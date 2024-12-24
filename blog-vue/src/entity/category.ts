export interface Category {
  categoryId: number
  categoryName: string
  isInterrupt: number
  categoryParent: number|null
}
export interface CategoryTree {
  categoryId: number
  categoryName: string
  isInterrupt: number
  categoryChildren: CategoryTree[]
}
