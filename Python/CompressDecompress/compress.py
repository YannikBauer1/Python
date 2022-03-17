from bitstring import *
import math
import sys

#inputFile="image.jpg"
inputFile=sys.argv[1].strip()
#outputFile="compressedImage"
outputFile=sys.argv[2].strip()

f = open(inputFile,"rb")
bitstring = Bits(f)
#bitstring=BitArray("0b101100111111111111110")

get_bin = lambda x, n: format(x, 'b').zfill(n)

S={"":0,bitstring.bin[0]:1}
output=BitArray("0b"+bitstring.bin[0])
bitstring=bitstring[1:]
x=""

for i in range(len(bitstring)):
    x = x + bitstring[i:i+1].bin
    if x in S:
        continue
    else:
        numBit = math.ceil(math.log(len(S), 2))
        i=S[x[:-1]]
        output.append("0b" + get_bin(i, numBit) + x[-1])
        S[x]=len(S)
        x = ""
if x!="":
    numBit = math.ceil(math.log(len(S), 2))
    i = S[x[:-1]]
    output.append("0b" + get_bin(i, numBit) + x[-1])

more0 = 8 - len(output.bin) % 8
if more0 == 8:
    output.append("0b00000000")
else:
    output.append("0b"+more0*"0"+get_bin(more0,8))

print(output.bin)

w=open(outputFile, "wb")
w.write(output.bytes)
w.close()