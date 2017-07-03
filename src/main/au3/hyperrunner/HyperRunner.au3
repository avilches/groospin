#Region ;**** Directives created by AutoIt3Wrapper_GUI ****
#AutoIt3Wrapper_Outfile=D:\Games\HyperSpin-fe\HyperRunner.exe
#EndRegion ;**** Directives created by AutoIt3Wrapper_GUI ****
; Basic idea for an installer.
#include <GUIConstantsEx.au3>
#include <TabConstants.au3>
#include <MsgBoxConstants.au3>
#include <Date.au3>
#include <File.au3>


$maxRuns = 2
$iniFile = "HyperRunner.ini"
$logFile = "HyperRunner.log.txt"

If $CmdLine[0] > 0 Then
	$iniFile = $CmdLine[1];
Endif

If Not FileExists($iniFile) Then
	MsgBox($MB_SYSTEMMODAL, "Error", $iniFile & " not found!")
	Exit
EndIf

if FileGetSize($logFile) > 1000000 Then
	FileDelete($logFile)
EndIf

myLog("----------------------------------------------------------")
myLog(_NowDate() & " " & _NowTime())
myLog("[" & $iniFile & "]")

For $i = 1 To $maxRuns Step 1
	Local $stop = IniRead($iniFile, "Stop", "Process" & $i, "")
	Local $prefix = "[Stop] Process" & $i & ": "
	If $stop <> "" Then
		If Not ProcessExists ($stop) Then
			myLog($prefix & $stop & " is not running")
		Else
			Local $result = ProcessClose($stop)
			If $result = 1 Then
				myLog($prefix & $stop & " stopped ok")
			Else
				myLog($prefix & $stop & " can't be stopped. Error: " & @error)
			EndIf
		EndIf
	EndIf
Next

For $i = 1 To $maxRuns Step 1
	Local $iniWrite = "IniWrite" & $i
	Local $file    = IniRead($iniFile, $iniWrite, "File", "")
	Local $section = IniRead($iniFile, $iniWrite, "Section", "")
	Local $key     = IniRead($iniFile, $iniWrite, "Key", "")
	Local $value   = IniRead($iniFile, $iniWrite, "Value", "")
	Local $prefix = "["& $iniWrite &"] "

	If $file <> "" And $section <> "" And $section <> "" And $section <> ""  Then
		If FileExists($file) Then
			IniWrite($file, $section, $key, $value)
			myLog($prefix & "Ini file " & $file & " updated ok:")
			myLog($prefix & "     [" & $section & "]")
			myLog($prefix & "     " & $key & "=" & $value)
		Else
			myLog($prefix & "Error: ini file not found: " & $file)
		Endif

	EndIf
Next

For $i = 1 To $maxRuns Step 1
	Local $run = IniRead($iniFile, "Run", "Cmd" & $i, "")
	Local $prefix = "[Run] Cmd" & $i & ": "
	If $run <> "" Then
		Local $pid = Run($run, "", @SW_HIDE)
		myLog($prefix & $run)
		If $pid > 0 Then
			myLog($prefix & "Pid: " & $pid)
		Else
			myLog($prefix & "Error running: " & @error)
		EndIf
	EndIf
Next

Func myLog($txt)
	Local $hFileOpen = FileOpen($logFile, $FO_APPEND)
	FileWriteLine($hFileOpen, $txt)
	FileClose($hFileOpen)
EndFunc
