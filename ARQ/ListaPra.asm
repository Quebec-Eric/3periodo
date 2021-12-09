#14 Considere  que  a  partir  da  primeira  posição  livre  da  memória  temos  um  vetor  com  100  elementos.
#Escrever um programa que ordene esse vetor de acordo com o algoritmo da bolha. Faça o teste colocando 
#um vetor totalmente desordenado e verifique se o algoritmo funciona. 
#s0 = 0  s1=100 s2 =4 t0=MEM t1=MEM+4  s3=NUM
.text main
main:
##vaviaveis
addi $s0,$zero,0 #vaviavel que ira variar
addi $s1,$zero,0 #tamanho
addi $s2,$zero,4 #
addi $s4,$zero,0 #vaviavel que ira variar
lui $t0,0x1001 #t0 = 0x10010000
add $t3,$zero,$t0
#add $t5,$zero,$t0
#fazendo o bolha 
lw $s1,($t0)
sub $s5,$s1,1
add $t0,$t0,$s2 # t0=t0+s2
mult $s1,$s2
mflo $t7
add $t7,$t0,$t7
  loop:
   # bge $s1,$s0,fim
   slt $t5,$s0,$s1 # if(s0<s1)-fim
   beq $t5,$zero,fim

     loop2:
         slt $t5,$s4,$s5  #if(s4<s1)-fim2/ acaba o fom de dentro
         beq $t5,$zero,fim2
         lw $s3($t0) #s3=mem[t0]
         slt $t5,$t3,$t7
          beq $t5,$zero,brek
         add $t3,$t3,$s2 # t3=t3+s2
         lw $t4($t3) #t4 =mem[t3]

        slt $t5,$t4,$s3 #if(t4<s3)
        beq $t5,$zero,fimSw
        sw $t4($t0)
        sw $s3($t3)
        fimSw:

        addi $s4,$s4,1 # s4++
        j loop2
        fim2:

    brek:

    add $t0,$t0,$s2 # t0=t0+s2
    add $t3,$zero,$t0 #t3=t0
    addi $s4,$zero,0  #s4 =0
    addi $s0,$s0,1 # s0++
    j loop

 fim:
.data
A: .word 20 34 21 23 2 10 15 68 30 14 29 3 48 46 49 61 48 80 16 1 44 7