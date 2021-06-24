/**
 * Copyright (c) 2021 Samsung Electronics Co., Ltd. All Rights Reserved.
 * <p>
 * Mobile Communication Division,
 * Digital Media & Communications Business, Samsung Electronics Co., Ltd.
 * <p>
 * This software and its documentation are confidential and proprietary
 * information of Samsung Electronics Co., Ltd.  No part of the software and
 * documents may be copied, reproduced, transmitted, translated, or reduced to
 * any electronic medium or machine-readable form without the prior written
 * consent of Samsung Electronics.
 * <p>
 * Samsung Electronics makes no representations with respect to the contents,
 * and assumes no responsibility for any errors that might appear in the
 * software and documents. This publication and the contents hereof are subject
 * to change without notice.
 *
 * <pre>
 *     author : yjie.liang
 *     time   : 2021/06/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */

/**
 * <pre>
 *     author : yjie.liang
 *     time   : 2021/06/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */

package com.yjie.architecture.common.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * des 替换 arr 中 AdapterListUpdateCallback
 *     仅更改 onMoved()实现
 * author zs
 * date 2021/4/12
 */
public class ListUpdateCallbackImp implements androidx.recyclerview.widget.ListUpdateCallback {
    @NonNull
    private final RecyclerView.Adapter mAdapter;

    /**
     * Creates an AdapterListUpdateCallback that will dispatch update events to the given adapter.
     *
     * @param adapter The Adapter to send updates to.
     */
    public ListUpdateCallbackImp(@NonNull RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    /** {@inheritDoc} */
    @Override
    public void onInserted(int position, int count) {
        mAdapter.notifyItemRangeInserted(position, count);
        if (mAdapter instanceof BaseDiffAdapter){
            BaseDiffAdapter adapter = (BaseDiffAdapter) mAdapter;
            if (adapter.getRecyclerView() == null || position != 0){
                return;
            }
            adapter.getRecyclerView().getLayoutManager().scrollToPosition(0);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onRemoved(int position, int count) {
        mAdapter.notifyItemRangeRemoved(position, count);
    }

    /** {@inheritDoc} */
    @Override
    public void onMoved(int fromPosition, int toPosition) {
        mAdapter.notifyDataSetChanged();
    }

    /** {@inheritDoc} */
    @Override
    public void onChanged(int position, int count, Object payload) {
        mAdapter.notifyItemRangeChanged(position, count, payload);
    }
}
