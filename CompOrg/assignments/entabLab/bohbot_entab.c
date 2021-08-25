#include <stdio.h>
#include <ctype.h>

int main() {
    int c;
    int hitFirstLetter = 0; // boolean for hitting a letter per line. Once this is true my code knows that the following text must not be altered. 
    int counter = 0; // I replaced column with counter because it was more clear for me. 
    while ((c = getchar() ) != EOF) {
        if (c ==  '\n') {
            // resets the counter and boolean to default so that it is prepared for the next line of text. 
            hitFirstLetter = 0;
            putchar(c);
            counter = 0;
        }
        else if (isblank(c) && hitFirstLetter == 0) {
            // check for boolean to know if text should still be altered. If blank then I adjust the counter. 
            counter ++;
            if (c == '\t') {
                // takes into account that a tab should be 4 spaces, and if there were spaces before the tab. 
                counter = counter + 3; // adjust only three bc I adjusted the counter up one already.
                int modCounter = counter % 4;
                counter = counter - modCounter;
                if (counter <= 0) {
                    // takes into account if the counter becomes negative.
                    counter = 4;
                }
            }
        }
        else if (!isblank(c) && hitFirstLetter == 0) {
            // I insert tabs here because it allows me to count total empty spaces until there first real content appears. 
            int tabs = counter / 4; // the amount of tabs that should be made.
            int i = 0;
            while (i <= tabs && counter != 0 && counter % 4 > 0) {
                // loops through how many times a tab should be inserted. 
                putchar('\t');
                i ++;
            }
            i = 0;
            while (counter % 4 == 0 && i < counter ) {
                // If exactly multiple of four, this fixes a bug I was receiving before. 
                if (i % 4 == 0) { putchar('\t'); }
                i ++;
            } 
            putchar(c);
            hitFirstLetter = 1; // now that I found my first non-empty char I do not want to adjust the rest of the text on current line. 
        }
        else { putchar(c); } // fills chars for remainding line. 
    }
    return 0;
}