#!/bin/bash
echo "Building VayuCast Weather Application..."

# Create output directory
mkdir -p out/production/VayuCast

# Compile Java files
echo "Compiling Java source files..."
javac -cp "lib/*" -d "out/production/VayuCast" src/com/vayucast/*.java

# Copy resources
echo "Copying resources..."
cp -r resources/* out/production/VayuCast/

echo "Build completed successfully!"
echo
echo "To run the application:"
echo "java -cp \"out/production/VayuCast:lib/*\" com.vayucast.Main"