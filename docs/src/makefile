
OUTPUT_FILES=jsr80.aux jsr80.lof jsr80.log jsr80.lot jsr80.toc jsr80.dvi jsr80.pdf

all: jsr80.pdf

jsr80.pdf: jsr80.dvi
	dvipdf jsr80.dvi jsr80.pdf

jsr80.dvi: jsr80.tex
	latex jsr80.tex
	latex jsr80.tex

clean:
	rm -f $(OUTPUT_FILES)
