$currentDirectory = Get-Location
$jarFile = "mfee.jar"
$foundJar = Get-ChildItem -Path $currentDirectory -Filter $jarFile -Recurse | Select-Object -First 1
if ($foundJar) {
    $javaExecutable = "java"
    Start-Process -FilePath $javaExecutable -ArgumentList "-jar", $foundJar.FullName
} else {
    Write-Host "The JAR file '$jarFile' was not found in the current directory or its subdirectories."
}