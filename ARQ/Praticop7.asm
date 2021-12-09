#Escreva um programa que avalie a expressão: (x*y)/z.  
#Use x = 1600000 (=0x186A00), y = 80000 (=0x13880), e z = 400000 (=0x61A80). 
#Inicializar os registradores com os valores acima. 
 
.text main 
main:
 addi $s0,$zero,0x18 # x
 sll $s0,$s0 ,16
 ori $s0,$s0,0x6A00
 
 addi $s1,$zero,0x13 #y
 sll $s1,$s1,12
 ori $s1,$s1,0x880

 addi $s2,$zero , 0x61 # z
 sll $s2,$s2,12
 ori $s2,$s2,0xA80
 
 
 div $s0,$s2	# x/z
 mflo $t0       # t0 = lo	
 div $s1,$s2    # y/z
 mflo $t1       # t1 =lo
 mult $t0 ,$t1
 mflo $t2 
	

.data 


 



 
