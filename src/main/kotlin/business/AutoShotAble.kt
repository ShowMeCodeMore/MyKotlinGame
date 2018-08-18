package business

import model.Bullet


/**
 * ClassName:AutoShotAble
 * Description:自动射击能力
 */
interface AutoShotAble {
    /**
     * 自动射击时间间隔
     */
    val timeLimit: Int

    /**
     * 自动射击
     * @return 子弹 可以为空
     */
    fun autoShot(): Bullet?
}