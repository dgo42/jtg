@echo off
set JAVA=%JAVA_HOME%\bin\java
rem 
rem "%JAVA%" -jar ../lib/antlr-4.5.3-complete.jar -Xlog -long-messages -message-format vs2005 -o ../java/org/edgo/jtg/core/grammar ..\grammar\TargetLexer.g4
rem 
rem "%JAVA%" -jar ../lib/antlr-4.5.3-complete.jar -Xlog -long-messages -message-format vs2005 -o ../java/org/edgo/jtg/core/grammar ..\grammar\JavaTemplateGrammar.g4

"%JAVA%" -jar ../lib/antlr-4.7.1-complete.jar -o ../java/org/edgo/jtg/core/grammar ..\grammar\JavaTemplateLexer.g4
rem 
"%JAVA%" -jar ../lib/antlr-4.7.1-complete.jar -o ../java/org/edgo/jtg/core/grammar ..\grammar\JavaTemplateGrammar.g4
