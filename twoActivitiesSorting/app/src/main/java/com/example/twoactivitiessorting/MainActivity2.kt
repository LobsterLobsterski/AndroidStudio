package com.example.twoactivitiessorting

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.twoactivitiessorting.databinding.ActivityMain2Binding


// receive user input from activity 1 and calculate whatever
// then return answer to activity 1
class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var sortedOutputs: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentData = intent.extras ?:return
        val dataInput = intentData.getString("dataInput")
        val sortsActive = intentData.getString("sortsActive")


        sort(dataInput, sortsActive)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun bubbleSort(arr: IntArray): IntArray {
        val n = arr.size

        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (arr[j] > arr[j + 1]) {
                    // Swap the elements
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
        return arr
    }

    private fun insertSort(arr: IntArray): IntArray{
        val n = arr.size
        for (i in 1 until n) {
            val key = arr[i]
            var j = i - 1
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]
                j--
            }
            arr[j + 1] = key
        }
        return arr
    }

    private fun mergeSort(array: IntArray, startIndex: Int = 0, endIndex: Int = array.size-1): IntArray {
        if (array.size <= 1) {
            return array
        }

        val middleIndex = (startIndex + endIndex) / 2
        val leftArray = array.sliceArray(startIndex..middleIndex)
        val rightArray = array.sliceArray(middleIndex + 1..endIndex)

        return merge(mergeSort(leftArray), mergeSort(rightArray))

    }

    private fun merge(leftArray: IntArray, rightArray: IntArray): IntArray{
        val mergedArray = IntArray(leftArray.size + rightArray.size)
        var leftIndex = 0
        var rightIndex = 0
        var mergedIndex = 0

        while (leftIndex < leftArray.size && rightIndex < rightArray.size) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                mergedArray[mergedIndex] = leftArray[leftIndex]
                leftIndex++
            } else {
                mergedArray[mergedIndex] = rightArray[rightIndex]
                rightIndex++
            }
            mergedIndex++
        }

        while (leftIndex < leftArray.size) {
            mergedArray[mergedIndex] = leftArray[leftIndex]
            leftIndex++
            mergedIndex++
        }

        while (rightIndex < rightArray.size) {
            mergedArray[mergedIndex] = rightArray[rightIndex]
            rightIndex++
            mergedIndex++
        }

        return mergedArray
    }

    private fun selectSort(arr: IntArray): IntArray{
        val n = arr.size

        for (i in 0 until n - 1) {
            var minIndex = i

            for (j in i + 1 until n) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j
                }
            }

            // Swap the found minimum element with the first element
            val temp = arr[minIndex]
            arr[minIndex] = arr[i]
            arr[i] = temp
        }

        return arr
    }

    private fun quickSort(arr: IntArray, left: Int = 0, right: Int = arr.size - 1): IntArray{
        var start = left
        var end = right
        val pivot = arr[(left + right) / 2]

        while (start <= end) {
            while (arr[start] < pivot) {
                start++
            }
            while (arr[end] > pivot) {
                end--
            }
            if (start <= end) {
                val temp = arr[start]
                arr[start] = arr[end]
                arr[end] = temp
                start++
                end--
            }
        }

        if (left < end) {
            quickSort(arr, left, end)
        }
        if (start < right) {
            quickSort(arr, start, right)
        }
        return arr
    }

    private fun sort(dataInput:String?, sortsActive:String?){
        val str = dataInput.toString().replace(" ", "")
        val strArr = str.split(',')
        var intArray = strArr.mapNotNull { it.toIntOrNull() }.toIntArray()

        if (intArray.isEmpty()){
            intArray = arrayOf(9, 0, -5, 23, 7, 3).toIntArray()
        }

        val activeSorts = sortsActive!!.split(',')

        sortedOutputs = ""

        if (!(activeSorts[0].toBooleanStrict()|| activeSorts[1].toBooleanStrict() ||
                    activeSorts[2].toBooleanStrict() || activeSorts[3].toBooleanStrict() ||
                    activeSorts[4].toBooleanStrict())){

            sortedOutputs =
                buildString {
                    append("Please select at least one sorting algorithm to use")
                }
        }
        else {
            //all sorting algorithms were copied from the internet, Baeldung mostly
            if (activeSorts[0].toBooleanStrict()) {
                sortedOutputs =
                    buildString {
                        append(sortedOutputs)
                        append(" \nBubbleSort: ")
                        append(bubbleSort(intArray).contentToString())
                    }
            }

            if (activeSorts[1].toBooleanStrict()) {
                sortedOutputs =
                    buildString {
                        append(sortedOutputs)
                        append(" \nInsertion Sort: ")
                        append(insertSort(intArray).contentToString())
                    }
            }
            if (activeSorts[2].toBooleanStrict()) {
                sortedOutputs =
                    buildString {
                        append(sortedOutputs)
                        append(" \nSelection Sort: ")
                        append(selectSort(intArray).contentToString())
                    }
            }
            if (activeSorts[3].toBooleanStrict()) {
                sortedOutputs =
                    buildString {
                        append(sortedOutputs)
                        append(" \nMerge Sort: ")
                        append(mergeSort(intArray).contentToString())
                    }
            }

            if (activeSorts[4].toBooleanStrict()) {
                sortedOutputs =
                    buildString {
                        append(sortedOutputs)
                        append(" \nQuick Sort: ")
                        append(quickSort(intArray).contentToString())
                    }
            }
        }
        finish()

    }

    override fun finish() {
        val data = Intent()
        data.putExtra("sortedOutputs", sortedOutputs)
        setResult(RESULT_OK, data)
        super.finish()
    }

}