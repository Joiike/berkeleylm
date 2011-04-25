package edu.berkeley.nlp.lm.map;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import edu.berkeley.nlp.lm.ContextEncodedNgramLanguageModel.LmContextInfo;
import edu.berkeley.nlp.lm.values.ValueContainer;

public abstract class AbstractNgramMap<T> implements NgramMap<T>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected final ValueContainer<T> values;

	protected final NgramMapOpts opts;

	protected final boolean reversed;

	protected AbstractNgramMap(final ValueContainer<T> values, final NgramMapOpts opts, final boolean reversed) {
		this.values = values;
		this.opts = opts;
		this.reversed = reversed;
	}

	protected static boolean equals(final int[] ngram, final int startPos, final int endPos, final int[] cachedNgram) {
		if (cachedNgram.length != endPos - startPos) return false;
		for (int i = 0; i < endPos - startPos; ++i) {
			if (ngram[startPos + i] != cachedNgram[i]) return false;
		}
		return true;
	}

	protected static int[] getSubArray(final int[] ngram, final int startPos, final int endPos) {
		return Arrays.copyOfRange(ngram, startPos, endPos);

	}

	protected static boolean containsOutOfVocab(final int[] ngram, final int startPos, final int endPos) {
		for (int i = startPos; i < endPos; ++i) {
			if (ngram[i] < 0) return true;
		}
		return false;
	}

	public ValueContainer<T> getValues() {
		return values;
	}

	public boolean isReversed() {
		return reversed;
	}

	public static class ValueOffsetPair<T>
	{

		public T getValue() {
			return value;
		}

		public long getOffset() {
			return offset;
		}

		/**
		 * @param value
		 * @param offset
		 */
		public ValueOffsetPair(T value, long offset) {
			super();
			this.value = value;
			this.offset = offset;
		}

		T value;

		long offset;
	}

}