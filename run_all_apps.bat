call mvnw.cmd clean
call mvnw.cmd install -DskipTests -pl common
if %errorlevel% neq 0 exit /b %errorlevel%
:: run all apps
start cmd /c mvnw.cmd spring-boot:run -pl news-producer
start cmd /c mvnw.cmd spring-boot:run -pl news-consumer