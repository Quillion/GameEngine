cls
color 8e

path C:\Program Files\Java\jdk1.7.0_04\bin;%path%

cls

javac *java

echo Main-Class: Main> manifest.txt
jar cvfm Main.jar manifest.txt *.class
Main.jar

del *.class

pause
