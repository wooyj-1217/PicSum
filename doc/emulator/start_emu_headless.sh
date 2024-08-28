# [Document] https://medium.com/innovies-club/run-android-emulator-out-of-the-box-with-github-actions-b84cba766e62
# [Document] https://github.com/amrsa1/android-emulator-workflow/blob/main/start_emu_headless.sh
#!/bin/bash

BL='\033[0;34m'
G='\033[0;32m'
RED='\033[0;31m'
YE='\033[1;33m'
NC='\033[0m' # No Color

emulator_name=${EMULATOR_NAME}

function check_hardware_acceleration() {
    if [[ "$HW_ACCEL_OVERRIDE" != "" ]]; then
        hw_accel_flag="$HW_ACCEL_OVERRIDE"
    else
        if [[ "$OSTYPE" == "darwin"* ]]; then
            # macOS-specific hardware acceleration check
            HW_ACCEL_SUPPORT=$(sysctl -a | grep -E -c '(vmx|svm)')
        else
            # generic Linux hardware acceleration check
            HW_ACCEL_SUPPORT=$(grep -E -c '(vmx|svm)' /proc/cpuinfo)
        fi

        if [[ $HW_ACCEL_SUPPORT == 0 ]]; then
            hw_accel_flag="-accel off"
        else
            hw_accel_flag="-accel on"
        fi
    fi

    echo "$hw_accel_flag"
}

hw_accel_flag=$(check_hardware_acceleration)

function launch_emulator () {
  adb devices | grep emulator | cut -f1 | xargs -I {} adb -s "{}" emu kill
  options="@${emulator_name} -no-window -no-snapshot -screen no-touch -noaudio -memory 2048 -no-boot-anim ${hw_accel_flag} -camera-back none -no-metrics"
  if [[ "$OSTYPE" == *linux* ]]; then
    echo "${OSTYPE}: emulator ${options} -gpu off"
    nohup emulator $options -gpu off &
  fi
  if [[ "$OSTYPE" == *darwin* ]] || [[ "$OSTYPE" == *macos* ]]; then
    echo "im here"
    echo "${OSTYPE}: emulator ${options} -gpu swiftshader_indirect"
    nohup emulator $options -gpu swiftshader_indirect &
  fi

  if [ $? -ne 0 ]; then
    echo "Error launching emulator"
    return 1
  fi
}

function check_emulator_status () {
  printf "${G}==> ${BL}Checking emulator booting up status 🧐${NC}\n"
  start_time=$(date +%s)
  # Get the timeout value from the environment variable or use the default value of 300 seconds (5 minutes)
  timeout=${EMULATOR_TIMEOUT:-300}

  # Start adb logcat and print logs directly to the console
  adb logcat -v time | while read -r line; do
    printf "$line"  # Print each log line to the console

    # Check for boot completion within the loop
    result=$(adb shell getprop sys.boot_completed 2>&1)
    if [ "$result" == "1" ]; then
      printf "\e[K${G}==> ✅ Emulator is ready : '$result'           ${NC}\n"
      adb devices -l
      adb shell input keyevent 82
      return  # Exit the loop once boot is complete
    fi

    current_time=$(date +%s)
    elapsed_time=$((current_time - start_time))
    if [ $elapsed_time -gt $timeout ]; then
      printf "${RED}==> ${timeout} 초가 지나 timeout 이 발생했습니다. 🕛.. ${NC}\n"
      break
    fi

    sleep 4
  done
};


function disable_animation() {
  adb shell "settings put global window_animation_scale 0.0"
  adb shell "settings put global transition_animation_scale 0.0"
  adb shell "settings put global animator_duration_scale 0.0"
};

function hidden_policy() {
  adb shell "settings put global hidden_api_policy_pre_p_apps 1;settings put global hidden_api_policy_p_apps 1;settings put global hidden_api_policy 1"
};

launch_emulator
sleep 2
check_emulator_status
sleep 1
disable_animation
sleep 1
hidden_policy
sleep 1