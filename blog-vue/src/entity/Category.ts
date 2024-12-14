export interface Category {
  categoryId?: number
  categoryName: string
  isInterrupt: number
  categoryParent: number
}
export interface CategoryTree {
  categoryId: number
  categoryName: string
  isInterrupt: number
  categoryChildren: CategoryTree[]
}
