/* 
 * CS:APP Data Lab 
 * 
 * <
 * Name:Tom Bohbot
 * User id: 800496822
 * >
 * 
 * bits.c - Source file with your solutions to the Lab.
 *          This is the file you will hand in to your instructor.
 *
 * WARNING: Do not include the <stdio.h> header; it confuses the dlc
 * compiler. You can still use printf for debugging without including
 * <stdio.h>, although you might get a compiler warning. In general,
 * it's not good practice to ignore compiler warnings, but in this
 * case it's OK.  
 */

#if 0
/*
 * Instructions to Students:
 *
 * STEP 1: Read the following instructions carefully.
 */

You will provide your solution to the Data Lab by
editing the collection of functions in this source file.

INTEGER CODING RULES:
 
  Replace the "return" statement in each function with one
  or more lines of C code that implements the function. Your code 
  must conform to the following style:
 
  int Funct(arg1, arg2, ...) {
      /* brief description of how your implementation works */
      int var1 = Expr1;
      ...
      int varM = ExprM;

      varJ = ExprJ;
      ...
      varN = ExprN;
      return ExprR;
  }

  Each "Expr" is an expression using ONLY the following:
  1. Integer constants 0 through 255 (0xFF), inclusive. You are
      not allowed to use big constants such as 0xffffffff.
  2. Function arguments and local variables (no global variables).
  3. Unary integer operations ! ~
  4. Binary integer operations & ^ | + << >>
    
  Some of the problems restrict the set of allowed operators even further.
  Each "Expr" may consist of multiple operators. You are not restricted to
  one operator per line.

  You are expressly forbidden to:
  1. Use any control constructs such as if, do, while, for, switch, etc.
  2. Define or use any macros.
  3. Define any additional functions in this file.
  4. Call any functions.
  5. Use any other operations, such as &&, ||, -, or ?:
  6. Use any form of casting.
  7. Use any data type other than int.  This implies that you
     cannot use arrays, structs, or unions.

 
  You may assume that your machine:
  1. Uses 2s complement, 32-bit representations of integers.
  2. Performs right shifts arithmetically.
  3. Has unpredictable behavior when shifting if the shift amount
     is less than 0 or greater than 31.


EXAMPLES OF ACCEPTABLE CODING STYLE:
  /*
   * pow2plus1 - returns 2^x + 1, where 0 <= x <= 31
   */
  int pow2plus1(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     return (1 << x) + 1;
  }

  /*
   * pow2plus4 - returns 2^x + 4, where 0 <= x <= 31
   */
  int pow2plus4(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     int result = (1 << x);
     result += 4;
     return result;
  }

FLOATING POINT CODING RULES

For the problems that require you to implement floating-point operations,
the coding rules are less strict.  You are allowed to use looping and
conditional control.  You are allowed to use both ints and unsigneds.
You can use arbitrary integer and unsigned constants. You can use any arithmetic,
logical, or comparison operations on int or unsigned data.

You are expressly forbidden to:
  1. Define or use any macros.
  2. Define any additional functions in this file.
  3. Call any functions.
  4. Use any form of casting.
  5. Use any data type other than int or unsigned.  This means that you
     cannot use arrays, structs, or unions.
  6. Use any floating point data types, operations, or constants.


NOTES:
  1. Use the dlc (data lab checker) compiler (described in the handout) to 
     check the legality of your solutions.
  2. Each function has a maximum number of operations (integer, logical,
     or comparison) that you are allowed to use for your implementation
     of the function.  The max operator count is checked by dlc.
     Note that assignment ('=') is not counted; you may use as many of
     these as you want without penalty.
  3. Use the btest test harness to check your functions for correctness.
  4. Use the BDD checker to formally verify your functions
  5. The maximum number of ops for each function is given in the
     header comment for each function. If there are any inconsistencies 
     between the maximum ops in the writeup and in this file, consider
     this file the authoritative source.

/*
 * STEP 2: Modify the following functions according the coding rules.
 * 
 *   IMPORTANT. TO AVOID GRADING SURPRISES:
 *   1. Use the dlc compiler to check that your solutions conform
 *      to the coding rules.
 *   2. Use the BDD checker to formally verify that your solutions produce 
 *      the correct answers.
 */


#endif
/* Copyright (C) 1991-2018 Free Software Foundation, Inc.
   This file is part of the GNU C Library.

   The GNU C Library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.

   The GNU C Library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.

   You should have received a copy of the GNU Lesser General Public
   License along with the GNU C Library; if not, see
   <http://www.gnu.org/licenses/>.  */
/* This header is separate from features.h so that the compiler can
   include it implicitly at the start of every compilation.  It must
   not itself include <features.h> or any other header that includes
   <features.h> because the implicit include comes before any feature
   test macros that may be defined in a source file before it first
   explicitly includes a system header.  GCC knows the name of this
   header in order to preinclude it.  */
/* glibc's intent is to support the IEC 559 math functionality, real
   and complex.  If the GCC (4.9 and later) predefined macros
   specifying compiler intent are available, use them to determine
   whether the overall intent is to support these features; otherwise,
   presume an older compiler has intent to support these features and
   define these macros by default.  */
/* wchar_t uses Unicode 10.0.0.  Version 10.0 of the Unicode Standard is
   synchronized with ISO/IEC 10646:2017, fifth edition, plus
   the following additions from Amendment 1 to the fifth edition:
   - 56 emoji characters
   - 285 hentaigana
   - 3 additional Zanabazar Square characters */
/* We do not support C11 <threads.h>.  */


/* 
 * bitNor - ~(x|y) using only ~ and & 
 *   Example: bitNor(0x6, 0x5) = 0xFFFFFFF8
 *   Legal ops: ~ &
 *   Max ops: 8
 *   Rating: 1
 */
int bitNor(int x, int y) { 
  /* 
   * bitNor is stated to be euqal to ~(x | y) 
   * ~(x|y) is equal to ~x & ~y through using DeMorgans Rules.
   * ~x & ~y is allowed through rules. 
   */
  int xComplement = ~x;
  int yComplement = ~y;
  int bitNor = xComplement & yComplement;   // This combines ~x and ~y together giving you bitNor. 
  return bitNor;
}


/* 
 * TMax - return maximum two's complement integer 
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 4
 *   Rating: 1
 */
int tmax(void) { 
  /*
   * two's complement of x is equal to ~x + 1.
   * the largest twos complement will be complement of 0x80000000
   */ 
  int maxComplement = 1 << 31; // this is equal to 0x80000000
  int tmax = ~maxComplement;   // this is equal to the largest twos complement. I reduced operations to not need to add 1. Not needed with my chosen initial variable.
  return tmax;
}

/* 
 * getByte - Extract byte n from word x
 *   Bytes numbered from 0 (least significant) to 3 (most significant)
 *   Examples: getByte(0x12345678,1) = 0x56
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 6
 *   Rating: 2
 */
int getByte(int x, int n) { 
  /*
   * Each hex digit is equal to a nibble, and 2 nibbles is equal to a byte.
   * This means that the hex number can be divided into 4 bytes, each byte being two hex digits.
   * Since int n is equal to the numbered byte I want, if I add n eight times I will be have the bit position I want.
   * If I then shift x over by my sum of n I will now have the byte I want in the 0th and first position.
   * I now must use a mask to only return the 0th and 1st bit position and return. 
   */
  int twoBits = n + n; // + n + n + n + n + n + n; // how many total bits should be shifted. 
  int nibble = twoBits + twoBits;
  int byte = nibble + nibble;
  int iWantLastTwoNumbers = x >> byte; 
  int twoActiveBytes = 255;
  int lastTwoDigits = iWantLastTwoNumbers & twoActiveBytes;
  return lastTwoDigits;
}

/* 
 * allEvenBits - return 1 if all even-numbered bits in word set to 1
 *   where bits are numbered from 0 (least significant) to 31 (most significant)
 *   Examples allEvenBits(0xFFFFFFFE) = 0, allEvenBits(0x55555555) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 2
 */
int allEvenBits(int x) { 
  /*
   * If x is a number with each even number bit position set 1, allEvenBits(int x) will return 1.
   * If x has all even bits then using or with all odd bits will return 0xffffffff.
   * The complement of 0xffffffff is 0x0.
   * The negation of 0 is equal to one. 
   * Therefore you will get one if a number with all even bits is enterd.
   * If a number does not have all even bits, then its complement in the code will not be zero.
   * The negation of a number != 0 is 0. Therefore it will return 0 if a number is entered without all even bits. 
   */
  int maxOnlyOddBits = 170; // cannot use a number with all odd bits, so I will shift over this number which is the largest number permissible with all odd bits. 
  int combineLastEightBits = x | maxOnlyOddBits;
  int firstShift = maxOnlyOddBits << 8;
  int combineLastSixteentBits = combineLastEightBits | firstShift;
  int secondShift = firstShift << 8;
  int combineLastTwentyFourBits = combineLastSixteentBits | secondShift;
  int finalShift = secondShift << 8;
  int combineThirtyTwoBits = combineLastTwentyFourBits | finalShift; // at this point I should have 0xffffffff if x has all even bit positions set to 1. I will have something != to 0xffffffff if not. 
  int onesComplement = ~combineThirtyTwoBits; // the complement will be 0 if all even bits are set of x are set to 1, will !=0 if not .
  int returnValue = !onesComplement; // the negation will return 1 if all even bits of x are set to 1, return 0 if not. 
  return returnValue;  
}

/*
 * ezThreeFourths - multiplies by 3/4 rounding toward 0,
 *   Should exactly duplicate effect of C expression (x*3/4),
 *   including overflow behavior.
 *   Examples: ezThreeFourths(11) = 8
 *             ezThreeFourths(-9) = -6
 *             ezThreeFourths(1073741824) = -268435456 (overflow)
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 3
 */
int ezThreeFourths(int x) { 
  /*
   * To calculate x times 3/4 I must add x three times then shift it 2 to the right since that is equivalent to dividing by four.
   * However, this method rounds to negative infinity and not zero.
   * This is not an issue for positive numbers but it is an issue for negative numbers. 
   * This is also only an issue when the number has a remainder > 0 when divided by four. 
   * Additionally, when x is added three times it can overflow to a negative number, so must check if the return value will be negative after summing x three times, not before.
   * If the number is negative and has a remainder > 0 then the rounding must be corrected.
   * This can be corrected by adding 3 to the x times 3. 
   */
    int threeTimesX = x + x + x;
    int remainder = threeTimesX & 3;                    // check threetimesx bc x after addition may have overflowed and is now a negative number. 
    int hasRemainder = !!remainder;                     // returns one if there is a remainder. Need remainder to know if there will be a rounding issue. 
    int sign = threeTimesX >> 31;                       // shifts the signed bit over to the first position (right to left).
    int adjustedSign = hasRemainder & sign;             // Adjusts sign to 0 if number is divisible by four. No rounding issue in this case. 
    int finalSign = adjustedSign | adjustedSign << 1;   // sets sign to three if threeTimesX negative to correct the rounding when the output is a negative int.
    int finalThreeTimesX = threeTimesX + finalSign;     // add final sign to correct rounding for negative numbers. When a negative number.
    int threeFourths = finalThreeTimesX >> 2;           // using >>2 divides by four. This is technically x * 3/4.
    return threeFourths;
}


/* 
 * logicalShift - shift x to the right by n, using a logical shift
 *   Can assume that 0 <= n <= 31
 *   Examples: logicalShift(0x87654321,4) = 0x08765432
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 3 
 */
int logicalShift(int x, int n) { 
  /*
   * In c programming language, when we shift it is arithmetic and not logical.
   * This means that shifting right will set all bits before the leftmost shifted bit will be set to 1. 
   * Instead of simplfy shifting x >> n, this algorithm must set all bits to the left of original leftmost bit to zero.
   */
    int shifted = x >> n; // leftmost bits are now set to f. Must be set to zero.
    int negatedN = ~n + 1;
    int maxBits = 31;
    int negativeOne = ~1 + 1;
    int bitsToKeepAsIs = maxBits + negatedN; // equal to how many bits you want to keep the same.
    int shiftAgain = (shifted >> bitsToKeepAsIs) + negativeOne; // equal to bits you want to get rid of with leading terms set to f.
    int finalShift = ~(shiftAgain << bitsToKeepAsIs); // equal to bits you want to get rid. Negated to switch bits so that leading bits are all zero now. 
    int unite = (finalShift) & (shifted); // union of these two will return number the user wants. 
    return unite;
}

/* 
 * greatestBitPos - return a mask that marks the position of the
 *               most significant 1 bit. If x == 0, return 0
 *   Example: greatestBitPos(96) = 0x40
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 70
 *   Rating: 4 
 */
int greatestBitPos(int x) { 
  /*
   * Goal is to return to the most signifcant bit.
   * This is achieved through replicating the left most bit a x contains in every bit to the right until the most unsignificant bit position. 
   * Once the significant bit is replicated it should be negated.
   * Then the inverse should be shifted one to the right so that it's greatest bit will have an intersection with itself prenegation, but nothing else will be in common.
   * Then there is a correction that must be made for negative numbers and then return. 
   */
    int isSigned = x >> 31 & 1;
    int signedBit = isSigned << 31;
    int offPut = isSigned << 30; // to account for negative input.
    int second = x | x >> 1;
    int fourth = second | second >> 2;
    int eigth  = fourth | fourth >> 4;
    int sixteenth = eigth | eigth >> 8;
    int thirtyTwo = sixteenth | sixteenth >> 16; // leftmost bit replicated itself everywhere to its right. If zero, then there are only zeros. 
    int inverseThirtyTwo = ~thirtyTwo | signedBit; // or the signed bit so that the complement will not equal zero when there is a bit at 0x80000000.
    int shiftInverseThirtyTwo = inverseThirtyTwo >> 1; // this shift is the key to the algorithm. It ensures that there will be one in common with old greatest bit. 
    int greatestBitPos = shiftInverseThirtyTwo & thirtyTwo; // the only intersection in these two numbers will be the greatest bit. 
    greatestBitPos = greatestBitPos ^ offPut; // xor right here to adjust code for negative numebrs. 
    return greatestBitPos;
}


/* 
 * isNonZero - Check whether x is nonzero using
 *              the legal operators except !
 *   Examples: isNonZero(3) = 1, isNonZero(0) = 0
 *   Legal ops: ~ & ^ | + << >>
 *   Max ops: 10
 *   Rating: 4 
 */
int isNonZero(int x) { 
  /*
   * To find out if x is nonZero the negation of the number must first be taken. This will only result in duplicate result when x is zero. 
   * Combine both signed bit of negation with signed bit of x to be returned one. The only case this will not return one is when the negation of zero is taken as that is still zero.
   * Return the union of these bits shifted and intersected with one. 
   */
    int twosComplement = ~x + 1; // causes all bits to left of actuvated bits to be turned on. 
    int combine = x | twosComplement; // use two's complement so that 0's complement is still zero.
    int shiftLastBitToFirstBit = combine >> 31;  // ensure that bits left most activated bit are moved up. moved it to beinning bc if there is an activated bit somewhere, it will now be the first bit.
    int isNonZero = shiftLastBitToFirstBit & 1; // and one to it to transform all the 'f' hex digits to '0'.
  return isNonZero;

}


/* 
 * floatNegate - Return bit-level equivalent of expression -f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representations of
 *   single-precision floating point values.
 *   When argument is NaN, return argument.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 10
 *   Rating: 2
 */
unsigned floatNegate(unsigned uf) { 
  /*
   * In single-precision floating point values the left most bit represents the signed bit.
   * If this bit is switched then it will return x negated.
   * However, there are some edge cases that must return themselves which are 0x7fc00000 and 0xffc00000. 
   * Additionally NaN must return zero. 
   */
  unsigned int signedBit;
  unsigned int negated;
  // return itself if NaN:
  if (uf == 0x7fc00000 || uf == 0xffc00000) { // these two numbers are NaN b/c all exponent bits are 1 and fraction bits are 0.
    return uf;
  }
  // else just flip the leftmost bit and return. 
  signedBit = 1 << 31; 
  negated = uf ^ signedBit;
  return negated;
}
