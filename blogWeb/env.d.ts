/// <reference types="vite/client" />
import { SlateElement } from '@wangeditor/editor'

declare module 'vue3-emoji'
declare module 'esdk-obs-browserjs'

type ImageElement = SlateElement & {
  src: string
  alt: string
  url: string
  href: string
}
