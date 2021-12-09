#23)Escreva um programa que solicite ao usuário que digite dois números, seu programa deverá conter uma 
#função que receba esses dois números e retorne o primeiro elevado ao segundo. Esse resultado deverá ser 
#mostrado na tela. O programa rodará indefinidamente até que o primeiro número digitado seja 0 (zero).  
#Obs.: Caso você não tenha visto a utilização de handlers e a leitura de valores pelo teclado, os dois 
#números deverão ser lidos da primeira e segunda posição livre da memória. O resultado será 
#escrito na terceira posição livre da memória e o programa irá executar apenas uma vez. 
#O valor retornado será a soma de todos os elementos de y[]. 
.text main
main:
#variaveis
lui $t0,0x1001
lw $s0,($t0) 
lw $s1,4($t0)
addi $t1,$zero,0
#fazer
beq $s1,1,sobrenada
mult $s0,$s0
mflo $t2
add $t1,$t1,2
do:
ble $s1,$t1,fim
mult $t2,$s0
mflo $t2
add $t1,$t1,1
j do

fim:
sw $t2,8($t0)
j end
sobrenada:
sw $s0,8($t0)
end:
.data
A:.word 4
A1:.word 4