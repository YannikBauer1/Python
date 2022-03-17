from bitstring import *
import math
import sys

inputFile=sys.argv[1].strip()
#inputFile="file.lz"
outputFile=sys.argv[2].strip()
#outputFile="testimage2.jpg"

f = open(inputFile,"rb")
bitstring = Bits(f)
#bitstring=BitArray("0b1000111000111101111010110")

i = int(bitstring.bin[-8:],2)
bitstring=bitstring[:-8-i]

S=[BitArray(""), bitstring[0:1]]
output=BitArray(bitstring[0:1])
bitstring=bitstring[1:]
x=""

while bitstring!="":
    numBit = math.ceil(math.log(len(S), 2))
    i=int(bitstring[:numBit].bin,2)
    output.append(S[i]+BitArray("0b"+bitstring[numBit:numBit+1].bin))
    S+= [S[i]+BitArray("0b"+bitstring[numBit:numBit+1].bin)]
    bitstring=bitstring[numBit+1:]

print(output)
w= open(outputFile, "wb")
w.write(output.bytes)
w.close()