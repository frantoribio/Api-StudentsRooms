@echo off
setlocal

:: Paso 1: Verificar si Docker está instalado
where docker >nul 2>&1
if %errorlevel% neq 0 (
    echo Docker no está instalado o no está en el PATH.
    pause
    exit /b 1
)

:: Paso 2: Ejecutar docker-compose desde la carpeta actual
start "Docker Compose" cmd /k "cd /d %~dp0 && docker-compose up --build"

:: Esperar 30 segundos para que los contenedores arranquen
echo Esperando 30 segundos para que Docker Compose inicialice...
timeout /t 30 /nobreak >nul

:: Paso 3: Verificar si el JAR existe
if exist "%~dp0demo-0.0.1-SNAPSHOT.jar" (
    start "Spring Boot App" cmd /k "cd /d %~dp0 && java -jar demo-0.0.1-SNAPSHOT.jar"
) else (
    echo El archivo demo-0.0.1-SNAPSHOT.jar no se encontró en la carpeta actual.
    pause
)

endlocal