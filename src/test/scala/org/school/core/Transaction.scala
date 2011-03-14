package org.school.core

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class TransactionSpec extends FlatSpec with ShouldMatchers {

	behavior of "A transaction"

	it should "initialize with varargs" in {
		val itemset = ItemSet[String]("class1", "class2")
		val transaction = Transaction[String](itemset, itemset)

		transaction.sets.length should be (2)
	}

	it should "initialize with a list" in {
		val itemset = ItemSet[String]("class1","class2")
		val itemsets = List[ItemSet[String]](itemset, itemset)
		val transaction = Transaction[String](itemsets)

		transaction.sets.length should be (2)
	}

	it should "produce all unique candidates" in {
        var items1 = ('a' to 'm').map { _.toString }.toList
        var items2 = ('g' to 'z').map { _.toString }.toList
		val transaction = Transaction(ItemSet(items1), ItemSet(items2))
		val candidates = transaction.allItems.toSet

		candidates.size should be (26)
	}

	it should "produce the correct minsup" in {
        var items  = ('a' to 'm').map { _.toString }.toList
		val values = items.map { x => (x, (x(0) - '`') * 0.1) }
		val support = MultipleSupport(values.toMap)
		val transaction = Transaction(ItemSet(items.take(10)), ItemSet(items.takeRight(10)))

		transaction.minsup(support) should be (0.1)
	}
}

