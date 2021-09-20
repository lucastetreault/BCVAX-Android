package ca.trackthevaxxed.vaxcheck.di

import ca.trackthevaxxed.vaxcheck.utils.SHCDecoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * [DecoderModule]
 *
 *
 * @author Pinakin Kansara
 */
@Module
@InstallIn(SingletonComponent::class)
class DecoderModule {

    /**
     * This method will provide dependency resolution for shcDecoder.
     *
     * @return SHCDecoder singleton instance
     */
    @Provides
    fun providesSHCDecoder() = SHCDecoder()
}