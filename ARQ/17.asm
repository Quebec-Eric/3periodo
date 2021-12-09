#programa 18 Para a expressão a seguir, escreva um programa que calcule o valor de k:   
#k = x y 
#Obs: Você poderá utilizar o exercício anterior. 
#O valor de x deve ser lido da primeira posição livre da memória e o valor de y deverá 
#lido da segunda posição livre. O valor de k, após calculado, deverá ainda ser escrito na 
#terceira posição livre da memória. 
#Dê  um  valor  para  x  e  y  (dê  valores  pequenos  !!)  e  use  o  MARS  para  verificar  a  
#quantidade de instruções conforme o tipo (ULA, Desvios, Mem ou Outras) 
 
.text
.globl main
main:

lui $t0,0x1001  # Primeiro lugar na memoria
lw $s0,0($t0)   # x = MEM{t0}
lw $s1,4($t0)   # y = MEM{4+t0}
addi $t1,$zero,1
mult $s0,$s0
mflo $t3
do:
add $t1,$t1,1
slt $t2,$t1,$s1
beq $t2 ,$zero,fim
mult $t3,$s0
mflo $t3

j do
      
fim:                  
sw $t3,8($t0)     
.data
x: .word 3
y: .word 4    