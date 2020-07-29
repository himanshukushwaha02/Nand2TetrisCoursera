// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.


(LOOP)
@KBD
D=M

@WHITE
D;JEQ // Jump to white if key press is 0

@8192
D=A
@tempw
M=D

(LOOP3)
@tempw
M=M-1
D=M
@LOOP
D;JLT

@SCREEN
D=A
@var
M=D

@tempw
D=M
@var
M=M+D
A=M
M=-1

@LOOP3
0;JMP


(WHITE)
@8192
D=A
@tempb
M=D

(LOOP2)
@tempb
M=M-1
D=M
@LOOP
D;JLT

@SCREEN
D=A
@var1
M=D

@tempb
D=M
@var1
M=M+D
A=M
M=0

@LOOP2
0;JMP

@LOOP
0;JMP