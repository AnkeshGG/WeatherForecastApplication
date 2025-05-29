@echo off
echo Building VayuCast Weather Application...

REM Create output directory
if not exist "out" mkdir out
if not exist "out\production" mkdir out\production
if not exist "out\production\VayuCast" mkdir out\production\VayuCast

REM Compile Java files
echo Compiling Java source files...
javac -cp "lib\*" -d "out\production\VayuCast" src\com\vayucast\*.java

REM Copy resources
echo Copying resources...
xcopy /E /I "resources" "out\production\VayuCast"

echo Build completed successfully!
echo.
echo To run the application:
echo java -cp "out\production\VayuCast;lib\*" com.vayucast.Main
pause