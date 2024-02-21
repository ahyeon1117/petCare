# 디렉토리가 존재하지 않으면 생성
if (-not (Test-Path -Path "backend\build\resources\main\static")) {
    New-Item -Path "backend\build\resources\main\static" -ItemType Directory
}

# frontend 디렉토리로 이동
Set-Location -Path "frontend"

# npm 빌드 실행
npm run build

# 상위 디렉토리로 이동
Set-Location -Path "..\backend"

# gradlew 빌드 실행
.\gradlew build --exclude-task test
