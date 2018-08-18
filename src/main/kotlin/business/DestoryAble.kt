package business

import model.View


/**
 * ClassName:DestoryAble
 * Description:销毁的能力
 */
interface DestoryAble:View {
    /**
     * 是否需要销毁
     * @return true 需要销毁 false不需要销毁
     */
    fun needDestory():Boolean

    /**
     * 销毁效果
     * @return 效果数组
     */
    fun destoryXg():Array<View>?{
        return null
    }
}