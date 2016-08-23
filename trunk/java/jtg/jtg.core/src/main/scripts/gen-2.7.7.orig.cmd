@echo off
rem 
java -cp ../lib/antlr-2.7.7.jar antlr.Tool -o ../java/org/edgo/jtg/core/grammar ..\grammar\TargetLexer.g
rem 
java -cp ../lib/antlr-2.7.7.jar antlr.Tool -o ../java/org/edgo/jtg/core/grammar ..\grammar\MacroLexer.g
rem 
java -cp ../lib/antlr-2.7.7.jar antlr.Tool -o ../java/org/edgo/jtg/core/grammar ..\grammar\JavaTemplateGrammar.g
