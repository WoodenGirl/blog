import { SlateDescendant, SlateElement, SlateText } from '@wangeditor-next/editor'

type ImageElement = SlateElement & {
  src: string
  alt: string
  url: string
  href: string
}

declare module '@wangeditor/editor' {
  // 扩展 Text
  interface SlateText {
    text: string
  }

  // 扩展 Element
  interface SlateElement {
    type: string
    children: SlateDescendant[]
  }
}
