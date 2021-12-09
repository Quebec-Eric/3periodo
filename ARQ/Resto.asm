#22)Escreva uma função que receba como argumentos 2 números inteiros de 32 bits. Essa função deverá 
#também retornar um inteiro. 
#O primeiro número recebido como parâmetro representa um endereço de memória e o segundo uma 
#quantidade de elementos. Sua função deverá criar um vetor que tem início nesse endereço de memória 
#(primeiro argumento) e a quantidade de elementos desse vetor dadas pelo segundo argumento. 
#Cada elemento do vetor é um elemento da série: 
#y[i] = 2i – 1 se i for par; 
#y[i] = i se i for impar. 
#O valor retornado será a soma de todos os elementos de y[]. 
.text main
main:
lui $t0,0x1001
addi $s0,$zero,20
add $a0,$zero,$t0
add $a1,$zero,$s0
jal SomaVetor
nop
add $s3,$zero,$v0
j end

SomaVetor:
addi $t0,$zero,0
addi $t2,$zero,2
do:
slt $t1,$t0,$a1
beq $t1,$zero,fim
lw $s1,($a0)
div $t0,$t2
mfhi $s4
beq $s4,$zero,par
add $v0,$zero,$t0
add $a0,$a0,4
add $t0,$t0,1
j do
par:
mult $t0,$t2
mflo $s1
sub $s1,$s1,1
add $v0,$v0,$s1
add $a0,$a0,4
add $t0,$t0,1
j do
fim:
jr $ra

end:
.data
A: .word 33 21 23 2 10 15 68 30 14 29 3 48 46 49 61 48 80 16 1 44 7