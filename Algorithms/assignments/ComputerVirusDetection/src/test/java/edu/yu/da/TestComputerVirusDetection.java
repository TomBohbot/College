package edu.yu.da;

import edu.yu.da.ComputerVirusDetection;
import edu.yu.da.ComputerVirusDetection.Virus;
import edu.yu.da.ComputerVirusDetection.VirusChecker;

import org.junit.*;
import static org.junit.Assert.*;

public class TestComputerVirusDetection {

    VirusChecker vc = new VirusChecker() {
        @Override
        public boolean areEqual(Virus virus1, Virus virus2) {
            return virus1.getCode().equals(virus2.getCode());
        }
    };

    VirusChecker vcs1 = new VirusChecker() {
        @Override
        public boolean areEqual(Virus virus1, Virus virus2) {
            return virus1.getCode().charAt(0) == virus2.getCode().charAt(0);
        }
    };

    VirusChecker vcs = new VirusChecker() {
        @Override
        public boolean areEqual(Virus virus1, Virus virus2) {
            return virus1.getCode().length() == virus2.getCode().length();
        }
    };

    VirusChecker vcsLower = new VirusChecker() {
        @Override
        public boolean areEqual(Virus virus1, Virus virus2) {
            return virus1.getCode().toLowerCase().equals(virus2.getCode().toLowerCase());
        }
    };

    @Test(expected = IllegalArgumentException.class)
	public void nullEverything() {                    
        ComputerVirusDetection.mostPrevalent(null, null);
	}

    @Test(expected = IllegalArgumentException.class)
	public void nullViruses() {          
        ComputerVirusDetection.mostPrevalent(null, vc);
	}

    @Test
	public void zeroElem () {
        Virus [] viruses = new Virus [] {};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vc);
        assertEquals("Testing one elem", -1, result);
    }

    @Test
	public void oneElem () {
        Virus [] viruses = new Virus [] {new Virus("1", vc)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vc);
        assertEquals("Testing one elem", 0, result);
    }

    @Test
	public void twoElemTrue () {
        Virus [] viruses = new Virus [] {new Virus("1", vc), new Virus("1", vc)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vc);
        assertEquals("Testing one elem", viruses[0], viruses[result]);
    }

    @Test
	public void twoElemFalse () {
        Virus [] viruses = new Virus [] {new Virus("1", vc), new Virus("2", vc)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vc);
        assertEquals("Testing one elem", -1, result);
    }

    @Test
	public void threeElemTrue () {
        Virus [] viruses = new Virus [] {new Virus("1", vc), new Virus("2", vc), new Virus("1", vc)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vc);
        assertEquals("Testing one elem", viruses[0], viruses[result]);
    }

    @Test
	public void threeElemFalse () {
        Virus [] viruses = new Virus [] {new Virus("1", vc), new Virus("2", vc), new Virus("3", vc)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vc);
        assertEquals("Testing one elem", -1, result);
    }

    @Test
	public void oneHalfOnes () {
        Virus [] viruses = new Virus [] {new Virus("1", vc), new Virus("1", vc), new Virus("1", vc), new Virus("1", vc), new Virus("2", vc), new Virus("2", vc), new Virus("1", vc), new Virus("2", vc)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vc);
        assertEquals("Testing 5 1s and 3 4s", 0, result);
    }

    @Test
	public void exactlyHalf () {
        Virus [] viruses = new Virus [] {new Virus("1", vc), new Virus("2", vc), new Virus("1", vc), new Virus("2", vc), new Virus("1", vc), new Virus("2", vc), new Virus("1", vc), new Virus("2", vc)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vc);
        assertEquals("Testing 5 1s and 3 4s", -1, result);
    }

    @Test
	public void exactlyHalfPlusOne() {
        Virus [] viruses = new Virus [] {new Virus("1", vc), new Virus("2", vc), new Virus("1", vc), new Virus("2", vc), new Virus("1", vc), new Virus("2", vc), new Virus("1", vc), new Virus("2", vc), new Virus("1", vc)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vc);
        assertEquals("Testing 5 1s and 3 4s", viruses[0], viruses[result]);
    }

    @Test
	public void threeElements() {
        Virus [] viruses = new Virus [] {new Virus("1", vc), new Virus("2", vc), new Virus("3", vc), new Virus("2", vc), new Virus("3", vc), new Virus("3", vc), new Virus("3", vc), new Virus("3", vc), new Virus("1", vc)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vc);
        assertEquals("Testing 5 3s", viruses[2], viruses[result]);
    }

    @Test
	public void threeElementsPlusOne() {
        Virus [] viruses = new Virus [] {new Virus("1", vc), new Virus("2", vc), new Virus("3", vc), new Virus("2", vc), new Virus("3", vc), new Virus("3", vc), new Virus("3", vc), new Virus("3", vc), new Virus("1", vc), new Virus("3", vc)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vc);
        assertEquals("Testing 5 3s", viruses[2], viruses[result]);
    }

    @Test
	public void stringsAllEqual() {
        Virus [] viruses = new Virus [] {new Virus("cat", vcs), new Virus("bat", vcs), new Virus("put", vcs), new Virus("but", vcs), new Virus("fat", vcs), new Virus("mat", vcs), new Virus("hut", vcs), new Virus("rat", vcs), new Virus("not", vcs)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vcs);
        assertEquals("a's", viruses[0], viruses[result]);
    }

    @Test
	public void strings() {
        Virus [] viruses = new Virus [] {new Virus("cat1", vcs), new Virus("bat1", vcs), new Virus("put1", vcs), new Virus("but", vcs), new Virus("fat1", vcs), new Virus("mat1", vcs), new Virus("hut", vcs), new Virus("rat", vcs), new Virus("not1", vcs)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vcs);
        assertEquals("4's", viruses[0], viruses[result]);
    }

    @Test
	public void strings3() {
        Virus [] viruses = new Virus [] {new Virus("cat12", vcs), new Virus("bat", vcs), new Virus("put", vcs), new Virus("but", vcs), new Virus("fat1", vcs), new Virus("mat1", vcs), new Virus("hut", vcs), new Virus("rat", vcs), new Virus("not1", vcs)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vcs);
        assertEquals("3's", viruses[1], viruses[result]);
    }

    @Test
	public void stringsNone() {
        Virus [] viruses = new Virus [] {new Virus("cat12", vcs), new Virus("bat1", vcs), new Virus("put", vcs), new Virus("but", vcs), new Virus("fat1", vcs), new Virus("mat1", vcs), new Virus("hut", vcs), new Virus("rat", vcs), new Virus("not1", vcs)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vcs);
        assertEquals("-1's", -1, result);
    }

    @Test
	public void toLowerVanilla() {
        Virus [] viruses = new Virus [] {new Virus("PUT", vcsLower), new Virus("cat", vcsLower), new Virus("cAt", vcsLower), new Virus("CAT", vcsLower), new Virus("fat1", vcsLower), new Virus("Cat", vcsLower), new Virus("hut", vcsLower), new Virus("CAt", vcsLower), new Virus("caT", vcsLower)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vcsLower);
        assertEquals("CAT", viruses[2], viruses[result]);
    }

    @Test
	public void toLowerNone() {
        Virus [] viruses = new Virus [] {new Virus("cat12", vcsLower), new Virus("bat", vcsLower), new Virus("put", vcsLower), new Virus("but", vcsLower), new Virus("fat1", vcsLower), new Virus("mat1", vcsLower), new Virus("hut", vcsLower), new Virus("rat", vcsLower), new Virus("not1", vcsLower)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vcsLower);
        assertEquals("none", -1, result);
    }

    @Test
	public void charTrue() {
        Virus [] viruses = new Virus [] {new Virus("abc", vcs1), new Virus("abc", vcs1), new Virus("abc", vcs1), new Virus("abc", vcs1), new Virus("bbb", vcs1), new Virus("aaa", vcs1), new Virus("vvv", vcs1), new Virus("sss", vcs1), new Virus("fff", vcs1)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vcs1);
        assertEquals("a's", viruses[0], viruses[result]);
    }

    @Test
	public void charFalse() {
        Virus [] viruses = new Virus [] {new Virus("dbc", vcs1), new Virus("mbc", vcs1), new Virus("hbc", vcs1), new Virus("abc", vcs1), new Virus("bbb", vcs1), new Virus("aaa", vcs1), new Virus("vvv", vcs1), new Virus("sss", vcs1), new Virus("fff", vcs1)};
		int result = ComputerVirusDetection.mostPrevalent(viruses, vcs1);
        assertEquals("a's", -1, result);
    }
}
