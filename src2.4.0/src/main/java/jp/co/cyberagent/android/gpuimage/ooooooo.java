package jp.co.cyberagent.android.gpuimage;

/* compiled from: GPUImageRGBDilationFilter */
public class ooooooo extends O0OOo {
    public static final String O000000o = "attribute vec4 position;\nattribute vec2 inputTextureCoordinate;\n\nuniform float texelWidthOffset; \nuniform float texelHeightOffset; \n\nvarying vec2 centerTextureCoordinate;\nvarying vec2 oneStepPositiveTextureCoordinate;\nvarying vec2 oneStepNegativeTextureCoordinate;\n\nvoid main()\n{\ngl_Position = position;\n\nvec2 offset = vec2(texelWidthOffset, texelHeightOffset);\n\ncenterTextureCoordinate = inputTextureCoordinate;\noneStepNegativeTextureCoordinate = inputTextureCoordinate - offset;\noneStepPositiveTextureCoordinate = inputTextureCoordinate + offset;\n}\n";
    public static final String O00000Oo = "attribute vec4 position;\nattribute vec2 inputTextureCoordinate;\n\nuniform float texelWidthOffset;\nuniform float texelHeightOffset;\n\nvarying vec2 centerTextureCoordinate;\nvarying vec2 oneStepPositiveTextureCoordinate;\nvarying vec2 oneStepNegativeTextureCoordinate;\nvarying vec2 twoStepsPositiveTextureCoordinate;\nvarying vec2 twoStepsNegativeTextureCoordinate;\n\nvoid main()\n{\ngl_Position = position;\n\nvec2 offset = vec2(texelWidthOffset, texelHeightOffset);\n\ncenterTextureCoordinate = inputTextureCoordinate;\noneStepNegativeTextureCoordinate = inputTextureCoordinate - offset;\noneStepPositiveTextureCoordinate = inputTextureCoordinate + offset;\ntwoStepsNegativeTextureCoordinate = inputTextureCoordinate - (offset * 2.0);\ntwoStepsPositiveTextureCoordinate = inputTextureCoordinate + (offset * 2.0);\n}\n";
    public static final String O00000o = "attribute vec4 position;\nattribute vec2 inputTextureCoordinate;\n\nuniform float texelWidthOffset;\nuniform float texelHeightOffset;\n\nvarying vec2 centerTextureCoordinate;\nvarying vec2 oneStepPositiveTextureCoordinate;\nvarying vec2 oneStepNegativeTextureCoordinate;\nvarying vec2 twoStepsPositiveTextureCoordinate;\nvarying vec2 twoStepsNegativeTextureCoordinate;\nvarying vec2 threeStepsPositiveTextureCoordinate;\nvarying vec2 threeStepsNegativeTextureCoordinate;\nvarying vec2 fourStepsPositiveTextureCoordinate;\nvarying vec2 fourStepsNegativeTextureCoordinate;\n\nvoid main()\n{\ngl_Position = position;\n\nvec2 offset = vec2(texelWidthOffset, texelHeightOffset);\n\ncenterTextureCoordinate = inputTextureCoordinate;\noneStepNegativeTextureCoordinate = inputTextureCoordinate - offset;\noneStepPositiveTextureCoordinate = inputTextureCoordinate + offset;\ntwoStepsNegativeTextureCoordinate = inputTextureCoordinate - (offset * 2.0);\ntwoStepsPositiveTextureCoordinate = inputTextureCoordinate + (offset * 2.0);\nthreeStepsNegativeTextureCoordinate = inputTextureCoordinate - (offset * 3.0);\nthreeStepsPositiveTextureCoordinate = inputTextureCoordinate + (offset * 3.0);\nfourStepsNegativeTextureCoordinate = inputTextureCoordinate - (offset * 4.0);\nfourStepsPositiveTextureCoordinate = inputTextureCoordinate + (offset * 4.0);\n}\n";
    public static final String O00000o0 = "attribute vec4 position;\nattribute vec2 inputTextureCoordinate;\n\nuniform float texelWidthOffset;\nuniform float texelHeightOffset;\n\nvarying vec2 centerTextureCoordinate;\nvarying vec2 oneStepPositiveTextureCoordinate;\nvarying vec2 oneStepNegativeTextureCoordinate;\nvarying vec2 twoStepsPositiveTextureCoordinate;\nvarying vec2 twoStepsNegativeTextureCoordinate;\nvarying vec2 threeStepsPositiveTextureCoordinate;\nvarying vec2 threeStepsNegativeTextureCoordinate;\n\nvoid main()\n{\ngl_Position = position;\n\nvec2 offset = vec2(texelWidthOffset, texelHeightOffset);\n\ncenterTextureCoordinate = inputTextureCoordinate;\noneStepNegativeTextureCoordinate = inputTextureCoordinate - offset;\noneStepPositiveTextureCoordinate = inputTextureCoordinate + offset;\ntwoStepsNegativeTextureCoordinate = inputTextureCoordinate - (offset * 2.0);\ntwoStepsPositiveTextureCoordinate = inputTextureCoordinate + (offset * 2.0);\nthreeStepsNegativeTextureCoordinate = inputTextureCoordinate - (offset * 3.0);\nthreeStepsPositiveTextureCoordinate = inputTextureCoordinate + (offset * 3.0);\n}\n";
    public static final String O00000oO = "precision highp float;\n\nvarying vec2 centerTextureCoordinate;\nvarying vec2 oneStepPositiveTextureCoordinate;\nvarying vec2 oneStepNegativeTextureCoordinate;\n\nuniform sampler2D inputImageTexture;\n\nvoid main()\n{\nlowp vec4 centerIntensity = texture2D(inputImageTexture, centerTextureCoordinate);\nlowp vec4 oneStepPositiveIntensity = texture2D(inputImageTexture, oneStepPositiveTextureCoordinate);\nlowp vec4 oneStepNegativeIntensity = texture2D(inputImageTexture, oneStepNegativeTextureCoordinate);\n\nlowp vec4 maxValue = max(centerIntensity, oneStepPositiveIntensity);\n\ngl_FragColor = max(maxValue, oneStepNegativeIntensity);\n}\n";
    public static final String O00000oo = "precision highp float;\n\nvarying vec2 centerTextureCoordinate;\nvarying vec2 oneStepPositiveTextureCoordinate;\nvarying vec2 oneStepNegativeTextureCoordinate;\nvarying vec2 twoStepsPositiveTextureCoordinate;\nvarying vec2 twoStepsNegativeTextureCoordinate;\n\nuniform sampler2D inputImageTexture;\n\nvoid main()\n{\nlowp vec4 centerIntensity = texture2D(inputImageTexture, centerTextureCoordinate);\nlowp vec4 oneStepPositiveIntensity = texture2D(inputImageTexture, oneStepPositiveTextureCoordinate);\nlowp vec4 oneStepNegativeIntensity = texture2D(inputImageTexture, oneStepNegativeTextureCoordinate);\nlowp vec4 twoStepsPositiveIntensity = texture2D(inputImageTexture, twoStepsPositiveTextureCoordinate);\nlowp vec4 twoStepsNegativeIntensity = texture2D(inputImageTexture, twoStepsNegativeTextureCoordinate);\n\nlowp vec4 maxValue = max(centerIntensity, oneStepPositiveIntensity);\nmaxValue = max(maxValue, oneStepNegativeIntensity);\nmaxValue = max(maxValue, twoStepsPositiveIntensity);\nmaxValue = max(maxValue, twoStepsNegativeIntensity);\n\ngl_FragColor = max(maxValue, twoStepsNegativeIntensity);\n}\n";
    public static final String O0000O0o = "precision highp float;\n\nvarying vec2 centerTextureCoordinate;\nvarying vec2 oneStepPositiveTextureCoordinate;\nvarying vec2 oneStepNegativeTextureCoordinate;\nvarying vec2 twoStepsPositiveTextureCoordinate;\nvarying vec2 twoStepsNegativeTextureCoordinate;\nvarying vec2 threeStepsPositiveTextureCoordinate;\nvarying vec2 threeStepsNegativeTextureCoordinate;\n\nuniform sampler2D inputImageTexture;\n\nvoid main()\n{\nlowp vec4 centerIntensity = texture2D(inputImageTexture, centerTextureCoordinate);\nlowp vec4 oneStepPositiveIntensity = texture2D(inputImageTexture, oneStepPositiveTextureCoordinate);\nlowp vec4 oneStepNegativeIntensity = texture2D(inputImageTexture, oneStepNegativeTextureCoordinate);\nlowp vec4 twoStepsPositiveIntensity = texture2D(inputImageTexture, twoStepsPositiveTextureCoordinate);\nlowp vec4 twoStepsNegativeIntensity = texture2D(inputImageTexture, twoStepsNegativeTextureCoordinate);\nlowp vec4 threeStepsPositiveIntensity = texture2D(inputImageTexture, threeStepsPositiveTextureCoordinate);\nlowp vec4 threeStepsNegativeIntensity = texture2D(inputImageTexture, threeStepsNegativeTextureCoordinate);\n\nlowp vec4 maxValue = max(centerIntensity, oneStepPositiveIntensity);\nmaxValue = max(maxValue, oneStepNegativeIntensity);\nmaxValue = max(maxValue, twoStepsPositiveIntensity);\nmaxValue = max(maxValue, twoStepsNegativeIntensity);\nmaxValue = max(maxValue, threeStepsPositiveIntensity);\n\ngl_FragColor = max(maxValue, threeStepsNegativeIntensity);\n}\n";
    public static final String O0000OOo = "precision highp float;\n\nvarying vec2 centerTextureCoordinate;\nvarying vec2 oneStepPositiveTextureCoordinate;\nvarying vec2 oneStepNegativeTextureCoordinate;\nvarying vec2 twoStepsPositiveTextureCoordinate;\nvarying vec2 twoStepsNegativeTextureCoordinate;\nvarying vec2 threeStepsPositiveTextureCoordinate;\nvarying vec2 threeStepsNegativeTextureCoordinate;\nvarying vec2 fourStepsPositiveTextureCoordinate;\nvarying vec2 fourStepsNegativeTextureCoordinate;\n\nuniform sampler2D inputImageTexture;\n\nvoid main()\n{\nlowp vec4 centerIntensity = texture2D(inputImageTexture, centerTextureCoordinate);\nlowp vec4 oneStepPositiveIntensity = texture2D(inputImageTexture, oneStepPositiveTextureCoordinate);\nlowp vec4 oneStepNegativeIntensity = texture2D(inputImageTexture, oneStepNegativeTextureCoordinate);\nlowp vec4 twoStepsPositiveIntensity = texture2D(inputImageTexture, twoStepsPositiveTextureCoordinate);\nlowp vec4 twoStepsNegativeIntensity = texture2D(inputImageTexture, twoStepsNegativeTextureCoordinate);\nlowp vec4 threeStepsPositiveIntensity = texture2D(inputImageTexture, threeStepsPositiveTextureCoordinate);\nlowp vec4 threeStepsNegativeIntensity = texture2D(inputImageTexture, threeStepsNegativeTextureCoordinate);\nlowp vec4 fourStepsPositiveIntensity = texture2D(inputImageTexture, fourStepsPositiveTextureCoordinate);\nlowp vec4 fourStepsNegativeIntensity = texture2D(inputImageTexture, fourStepsNegativeTextureCoordinate);\n\nlowp vec4 maxValue = max(centerIntensity, oneStepPositiveIntensity);\nmaxValue = max(maxValue, oneStepNegativeIntensity);\nmaxValue = max(maxValue, twoStepsPositiveIntensity);\nmaxValue = max(maxValue, twoStepsNegativeIntensity);\nmaxValue = max(maxValue, threeStepsPositiveIntensity);\nmaxValue = max(maxValue, threeStepsNegativeIntensity);\nmaxValue = max(maxValue, fourStepsPositiveIntensity);\n\ngl_FragColor = max(maxValue, fourStepsNegativeIntensity);\n}\n";

    public ooooooo(int i) {
    }

    private ooooooo(String str, String str2) {
    }

    private static String O000000o(int i) {
        return null;
    }

    private static String O00000Oo(int i) {
        return null;
    }
}
