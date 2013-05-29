cls
color 8e

IF EXIST QLibrary.jar (
del QLibrary.jar
)

path C:\Program Files\Java\jdk1.7.0_04\bin;%path%

cd ..\..\Library\
javac QLibrary\*.java
jar cvf QLibrary.jar QLibrary\*.class
del QLibrary\*.class
cd ..\examples\basics
copy ..\..\Library\QLibrary.jar QLibrary.jar

cls

javac -cp ".;QLibrary.jar" *java
del *.class

pause
