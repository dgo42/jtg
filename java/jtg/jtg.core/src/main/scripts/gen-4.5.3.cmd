@echo off
rem 
rem java -jar ../lib/antlr-4.5.3-complete.jar -Xlog -long-messages -message-format vs2005 -o ../java/org/edgo/jtg/core/grammar ..\grammar\TargetLexer.g4
rem 
rem java -jar ../lib/antlr-4.5.3-complete.jar -Xlog -long-messages -message-format vs2005 -o ../java/org/edgo/jtg/core/grammar ..\grammar\JavaTemplateGrammar.g4

java -jar ../lib/antlr-4.5.3-complete.jar -o ../java/org/edgo/jtg/core/grammar ..\grammar\JavaTemplateLexer.g4
rem 
java -jar ../lib/antlr-4.5.3-complete.jar -o ../java/org/edgo/jtg/core/grammar ..\grammar\JavaTemplateGrammar.g4
