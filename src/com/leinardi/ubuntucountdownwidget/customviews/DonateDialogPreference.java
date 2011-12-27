/**
 *  Kitchen Timer
 *  Copyright (C) 2010 Roberto Leinardi
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  
 */

package com.leinardi.ubuntucountdownwidget.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;

import com.leinardi.analytics.AnalyticsUtils;
import com.leinardi.ubuntucountdownwidget.R;
import com.leinardi.ubuntucountdownwidget.utils.Utils;

public class DonateDialogPreference extends DialogPreference {
    Context mContext;
    String fileName;

    public DonateDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.MyDialogPreference);
        fileName = a.getString(R.styleable.MyDialogPreference_fileName);
    }

    // protected void onPrepareDialogBuilder(Builder builder) {
    // builder.setView(Utils.dialogWebView(mContext, fileName));
    // }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        AnalyticsUtils.getInstance(mContext).trackEvent(
                "DonateDialog", "ButtonSelected", positiveResult ? "Yes" : "No", 0);

        if (positiveResult) {
            Utils.getInstance().donate(mContext);
        }
    }
}
