/*
 * Autopsy
 *
 * Copyright 2022 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.texttranslation.translators.settings;

import java.nio.file.Paths;
import org.sleuthkit.autopsy.coreutils.PlatformUtil;
import com.google.common.annotations.Beta;

/**
 * ModuleSettings keys and paths for translator settings.
 */
 @Beta
public class TranslatorSettings {

    private static final String TRANSLATION_FOLDER = "Translation";
    private static final String TRANSLATION_PATH = Paths.get(PlatformUtil.getUserConfigDirectory(), TRANSLATION_FOLDER).toString();

    private static TranslatorSettings instance = new TranslatorSettings();

    /**
     * @return The singular instance.
     */
    public static TranslatorSettings getInstance() {
        return instance;
    }

    private TranslatorSettings() {
    }

    /**
     * Returns the resource execution context to be used with ModuleSettings.
     *
     * @param translationResource The name of the resource to be used with
     *                            ModuleSettings.
     *
     * @return The resource name to use with ModuleSettings.
     */
    public String getModuleSettingsResource(String translationResource) {
        return Paths.get(TRANSLATION_FOLDER, translationResource).toString();
    }

    /**
     * @return The base translation folder.
     */
    public String getBaseTranslationFolder() {
        return TRANSLATION_PATH;
    }
}
