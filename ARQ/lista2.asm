#24)Você deverá criar duas funções nesse exercício. Uma função que receba como 
#argumentos 2 números inteiros de 32 bits. Essa função deverá também retornar um 
#inteiro.  O primeiro número recebido como parâmetro representa um endereço de 
#memória e o segundo uma quantidade de elementos. A quantidade de elementos 
#máxima é 30, se o numero recebido for superior a 30 sua função deverá usar 30. 
#Os dois números acima deverão estar nas duas primeiras posições livres da memória 
#(portanto devem ser lidos da memória para serem passados à função). 
#Sua função deverá criar um vetor que tem início no endereço de memória recebido 
#como primeiro argumento e com a quantidade de elementos recebida como o segundo 
#argumento.  
#Uma segunda função que receba um número (este número terá no máximo 16 bits)  e 
#retorne o seu quadrado. 
#Cada elemento do vetor y será dado como:  
#y[i] = 2i2 +2i+1 se i for par;  
#y[i] = i2 se i for impar.  
#O valor retornado será a soma de todos os elementos de y[].

.text
.globl main
main:

lui $t0,0x1001
lw $a0,($t0)
lw $a1,4($t0)
jal vetorSoma
j endPrograma



vetorSoma:
addi $t0,$zero,0
addi $s0,$zero,2
addi $t1,$zero,30
addi $t4,$zero,0
add $s2,$zero,$a0 # local da memoria
add $s3,$zero,$a1
ble $s3,$t1,next
add $s3,$zero,$t1 #quantidade
next:
addi $sp, $sp, -4 
sw $ra, 4 ($sp) 

do:
   slt $t5,$t0,$s3
   beq $t5,$zero,fim
      div $t0,$s0
      mfhi $t2 
      beq $t2,$zero,par

         add $a0,$zero,$t0
         jal fazerQuadrado
         sw $v0,($s2)
         add $t0,$t0,1
         add $s2,$s2,4
	 add $t4,$t4,$v0
         j do
      par:
         add $a0,$zero,$t0
	 jal fazerQuadrado
         mult $t0,$s0
	 mflo $s4
         add $s4,$s4,$v0	
         add $s4,$s4,1
         sw $s4,($s2)  
         add $t0,$t0,1
         add $s2,$s2,4
	 add $t4,$t4,$s4 
         j do
 fim:
add  $v0, $t4, $zero
lw $ra, 4 ($sp)
addi $sp, $sp, 4
jr $ra 

fazerQuadrado:
mult $a0,$a0
mflo $v0
jr $ra

endPrograma:

.data 
A: .word 0x10010008 5