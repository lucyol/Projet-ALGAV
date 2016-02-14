#!/usr/bin/gnuplot

reset
set terminal png
set output "img/timeAdd.png"
set boxwidth 0.75 absolute
set xlabel "NB words"
set ylabel "Time (ms)"
set grid
plot "data/timeAdd" using 1:2 title 'Arbre Briandais' with linespoints, \
     "data/timeAdd2" using 1:3 title 'Trie Hybride' with linespoints
     
     
