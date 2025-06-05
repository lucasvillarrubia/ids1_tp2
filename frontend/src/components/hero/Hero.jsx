import React from 'react'
import { HeroUI, HeroBGBlur, HeroTitle, HeroButton } from './HeroStyles'

const Hero = () => {
  return (
    <HeroUI>
        <HeroBGBlur>
                <HeroTitle>IDS1</HeroTitle>
                <HeroButton>Sprint 2!</HeroButton>
        </HeroBGBlur>
    </HeroUI>
  )
}

export default Hero