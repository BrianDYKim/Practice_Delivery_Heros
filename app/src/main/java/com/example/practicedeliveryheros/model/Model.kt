package com.example.practicedeliveryheros.model

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

/**
 * Model은 데이터에 대한 추상 클래스로 정의가 되어야한다.
 * 기본적으로, Model은 id, type 성분이 필요하다
 */
abstract class Model(open val id: Long, open val type: CellType) {

    // ListAdapter에서는 DIFF_CALLBACK을 전달해야만 모델에 대한 리스트를 원활하게 처리가 가능하다
    companion object {
        @SuppressLint("DiffUtilEquals")
        val DIFF_CALLBACK : DiffUtil.ItemCallback<Model> = object : DiffUtil.ItemCallback<Model>() {

            override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
                return oldItem.id == newItem.id && oldItem.type == newItem.type // 두 아이템의 아이디와 타입을 동시 비교해야 같은 아이템인지 비교 가능
            }

            override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
                return oldItem === newItem
            }
        }
    }
}