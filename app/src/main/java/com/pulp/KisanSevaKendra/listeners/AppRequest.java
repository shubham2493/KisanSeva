/*
 * Copyright (c) 2016 Pulp Strategy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pulp.KisanSevaKendra.listeners;

import com.pulp.KisanSevaKendra.io.http.BaseTask;
import com.pulp.KisanSevaKendra.utils.Constants;

/**
 * Created by apple on 02/08/16.
 */
public interface AppRequest {
    public <T> void onRequestStarted(BaseTask<T> listener, Constants.RequestParam requestParam);

    public <T> void onRequestCompleted(BaseTask<T> listener, Constants.RequestParam requestParam);

    public <T> void onRequestFailed(BaseTask<T> listener, Constants.RequestParam requestParam);

}
