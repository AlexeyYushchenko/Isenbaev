Problem.
Vladislav Isenbaev is a two-time programming champion of the Urals, vice-champion of TopCoder Open 2009, 
absolute champion of ACM ICPC 2009. In the time you spend reading this condition, 
Vladislav would have already solved one problem. Or maybe two...
Since Vladislav Isenbaev is a graduate of the USU SUNC, it is not surprising that many of the former 
and current USU Olympiads have known him for many years. Some of them proudly declare that they played 
with Vladislav in the same team. Or played in a team with Vladislav's former teammates ...
We define the Isenbaev number as follows. Vladislav himself has this number equal to zero. 
For those who played with him on the same team, it is equal to one. 
For those who played together with Vladislav's teammates, but did not play with him himself, 
this number is two, and so on. Help automate the process of calculating Isenbaev's numbers 
so that every olympiad at USU can know how close he is to the ACM ICPC champion.

Initial data
The first line contains an integer n — the number of commands (1 ≤ n ≤ 100). 
Each of the next n lines contains the compositions of these teams in the form of the names of three participants. 
The last name of each participant is a non-empty string consisting of English letters, no more than 20 characters long. 
The first letter of the surname is capital, all the rest are lowercase. Vladislav's surname is "Isenbaev".

Result
For each participant presented in the input, print on a separate line his last name and Isenbaev's number separated by a space. 
If this number is not defined, print "undefined" instead. 
Participants must be ordered by last name in lexicographic order.

Sample Input
7
Isenbaev Oparin Toropov
Ayzenshteyn Oparin Samsonov
Ayzenshteyn Chevdar Samsonov
Fominykh Isenbaev Oparin
Dublennykh Fominykh Ivankov
Burmistrov Dublennykh Kurpilyanskiy
Cormen Leiserson Rivest

Sample Output 
Ayzenshteyn 2
Burmistrov 3
Chevdar 3
Cormen undefined
Dublennykh 2
Fominykh 1
Isenbaev 0
Ivankov 2
Kurpilyanskiy 3
Leiserson undefined
Oparin 1
Rivest undefined
Samsonov 2
Toropov 1
