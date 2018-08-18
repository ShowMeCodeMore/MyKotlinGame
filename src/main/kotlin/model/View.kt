package model


/**
 * ClassName:View
 * Description:所有控件的父类
 */
interface View {
    //比较排序字段
    val code: Int
    var x: Int
    var y: Int

    var width: Int
    var height: Int
    /**
     * 显示绘制
     */
    fun draw()
}