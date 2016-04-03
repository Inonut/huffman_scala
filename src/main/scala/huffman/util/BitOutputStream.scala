package huffman.util

import java.io.{BufferedOutputStream, OutputStream}
;

class BitOutputStream(os: OutputStream) extends BufferedOutputStream(os){

	private var currentByte = 0
	private var nbBits = 0

	def writeBit(b: Int) {
		if(b != 0 && b != 1){
			throw new Exception("b is not 0 or 1")
		}
		currentByte = currentByte << 1 | b
		nbBits += 1
		if (nbBits == 8) {
			super.write(currentByte)
			nbBits = 0
			currentByte=0
		}
	}

	def flushBit() {
		while(nbBits != 0){ writeBit(0) }
	}
	
}