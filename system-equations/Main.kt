typealias Matrix = Array<Array<Double>>

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {

  fun createLaplaceMarix(matrix: Matrix, n: Int, line: Int, col: Int): Matrix {

    val laplaceMarix: Matrix = Array(n - 1) { Array(n - 1) { 0.0 } }
    var y = 0
    println(matrix.map { it.toList() }.toList())
    for (i in 0..n - 1) {
      if (i == col) continue
      var x = 0
      for (j in 0..n - 1) {
        if (i == line || j == col) continue
        if (j == line) continue
        println("i=$i j=$j x=$x y=$y")

        laplaceMarix[y][x] = matrix[i][j]
        x++
      }
      y++
    }
    return laplaceMarix
  }

  fun dat(matrix: Matrix, n: Int): Double {

    if (matrix.size == 1) return matrix[0][0]
    if (matrix.size == 2) return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1]
    var sum: Double = 0.0
    for (j in 0..matrix.size - 1) {
      val sign = if ((j % 2) == 0) 1.0 else -1.0
      val laplaceMarix = createLaplaceMarix(matrix, n, 0, j)
      sum += sign * matrix[j][0] * dat(laplaceMarix, n - 1)
    }
    return sum
  }
  fun swapCol(matrix: Matrix, n: Int, col1: Int, col2: Int): Matrix {
    val tempMat: Matrix = Array(n) { Array(n + 1) { 0.0 } }
    for (i in 0..n - 1) {
      for (j in 0..n) {
        tempMat[i][j] = matrix[i][j]
      }
    }

    for (i in 0..n - 1) {
      val temp = tempMat[i][col1]
      tempMat[i][col1] = tempMat[i][col2]
      tempMat[i][col2] = temp
    }
    return tempMat
  }

  fun solve(matrix: Matrix, d: Int): Array<Double> {
    val denominator = dat(matrix, d)
    val result = Array(d) { 0.0 }
    if (denominator == 0.0) throw Exception("Denominator is zero")
    for (i in 0..d - 1) {
      val numeMat = swapCol(matrix, d, i, d)
      result[i] = dat(numeMat, d) / denominator
    }
    return result
  }

  val matrix = arrayOf(arrayOf(100.0, 400.0, 300.0), arrayOf(1.0, 3.0, 3.0))
  // val matrix = arrayOf(arrayOf(100, 200, 300, 600), arrayOf(1, 2, 3, 6), arrayOf(10, 20, 30, 60))
  val res = solve(matrix, 2)
  println(res.toList())
}
