const htmlToValue = (value: string) => {
  return value.replace(/<br\/>/g, "\n")
    .replace(/&nbsp;/g, " ")
    .replace(/&lt;$1&gt;/gi, "<(.+?)>");
}
const valueToHtml = (value: string) => {
  return value.replace(/<(.+?)>/gi, "&lt;$1&gt;")
    .replace(/ /gi, "&nbsp;")
    .replace(/\n/gi, "<br/>");
}
export {htmlToValue, valueToHtml}
