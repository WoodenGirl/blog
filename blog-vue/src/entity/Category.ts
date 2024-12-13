interface Category {
  categoryId?: number
  categoryName: string
  isInterrupt: number
  categoryParent: number
}
interface CategoryTree {
  categoryId: number
  categoryName: string
  isInterrupt: number
  categoryChildren: CategoryTree[]
}
