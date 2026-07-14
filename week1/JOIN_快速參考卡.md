# MySQL JOIN 語法快速參考卡

## 🔗 JOIN 類型速查表

| JOIN 類型 | 語法 | 結果說明 | 使用場景 |
|-----------|------|----------|----------|
| **INNER JOIN** | `FROM A INNER JOIN B ON condition` | 只返回兩表都匹配的記錄 | 查詢有關聯的資料 |
| **LEFT JOIN** | `FROM A LEFT JOIN B ON condition` | 返回左表全部 + 右表匹配 | 查詢主表資料，包含沒有關聯的 |
| **RIGHT JOIN** | `FROM A RIGHT JOIN B ON condition` | 返回右表全部 + 左表匹配 | 較少使用，可用 LEFT JOIN 替代 |
| **CROSS JOIN** | `FROM A CROSS JOIN B` | 笛卡爾積，所有組合 | 特殊分析需求 |
| **SELF JOIN** | `FROM A a1 JOIN A a2 ON condition` | 表與自己 JOIN | 階層結構、同表比較 |

## 📝 常用語法模板

### 基本 INNER JOIN
```sql
SELECT a.column1, b.column2
FROM table_a a
INNER JOIN table_b b ON a.id = b.foreign_id
WHERE condition;
```

### LEFT JOIN 找出缺失資料
```sql
SELECT a.column1, b.column2
FROM table_a a
LEFT JOIN table_b b ON a.id = b.foreign_id
WHERE b.foreign_id IS NULL;  -- 找出 A 有但 B 沒有的
```

### 多表 JOIN
```sql
SELECT a.col1, b.col2, c.col3
FROM table_a a
INNER JOIN table_b b ON a.id = b.a_id
INNER JOIN table_c c ON b.id = c.b_id
WHERE condition;
```

### SELF JOIN (階層查詢)
```sql
SELECT 
    emp.name AS employee,
    mgr.name AS manager
FROM employees emp
LEFT JOIN employees mgr ON emp.manager_id = mgr.id;
```

## 🎯 JOIN 條件寫法

### 相等條件
```sql
ON table1.column = table2.column
```

### 多個條件
```sql
ON table1.col1 = table2.col1 
   AND table1.col2 = table2.col2
```

### 範圍條件
```sql
ON table1.date BETWEEN table2.start_date AND table2.end_date
```

### 複雜條件
```sql
ON table1.id = table2.foreign_id 
   AND table1.status = 'active'
   AND table2.type IN ('A', 'B')
```

## 🚀 性能優化要點

### ✅ 最佳實踐
- 在 JOIN 條件欄位上建立索引
- 使用具體欄位名稱而非 `SELECT *`
- 先篩選再 JOIN (WHERE 條件)
- 小表驅動大表

### ❌ 避免事項
- 在大表上進行 CROSS JOIN
- JOIN 條件中使用函數
- 過多的表 JOIN (超過 5-7 個)

## 💡 常見問題解決

### 找出重複資料
```sql
SELECT column, COUNT(*)
FROM table
GROUP BY column
HAVING COUNT(*) > 1;
```

### 找出孤立記錄
```sql
SELECT a.*
FROM table_a a
LEFT JOIN table_b b ON a.id = b.foreign_id
WHERE b.foreign_id IS NULL;
```

### 統計每組的數量
```sql
SELECT 
    a.group_column,
    COUNT(b.id) AS count
FROM table_a a
LEFT JOIN table_b b ON a.id = b.foreign_id
GROUP BY a.group_column;
```

## 🔍 除錯技巧

### 檢查 JOIN 結果
```sql
-- 1. 先檢查各表的記錄數
SELECT COUNT(*) FROM table_a;
SELECT COUNT(*) FROM table_b;

-- 2. 檢查 JOIN 結果數量
SELECT COUNT(*) 
FROM table_a a
INNER JOIN table_b b ON a.id = b.foreign_id;

-- 3. 使用 EXPLAIN 分析性能
EXPLAIN SELECT ... FROM ... JOIN ...;
```

### 處理 NULL 值
```sql
-- 使用 COALESCE 處理 NULL
SELECT 
    a.name,
    COALESCE(b.value, 0) AS value  -- NULL 時顯示 0
FROM table_a a
LEFT JOIN table_b b ON a.id = b.foreign_id;
```

## 📊 JOIN 結果預期

### 表 A (3 筆) + 表 B (2 筆匹配)
- **INNER JOIN**: 2 筆 (只有匹配的)
- **LEFT JOIN**: 3 筆 (A 全部，B 匹配的 + NULL)
- **RIGHT JOIN**: 2 筆 (B 全部，A 匹配的)
- **CROSS JOIN**: 6 筆 (3 × 2 笛卡爾積)

## 🎓 學習建議

1. **從簡單開始**: 先掌握 INNER JOIN 和 LEFT JOIN
2. **多練習**: 使用實際資料練習各種 JOIN
3. **理解業務邏輯**: 了解資料表之間的關係
4. **關注性能**: 學會使用 EXPLAIN 分析查詢
5. **循序漸進**: 先雙表 JOIN，再進階到多表

---

## 🔧 實用 SQL 片段

### 複製一份用於練習
```sql
-- 建立測試表的備份
CREATE TABLE customers_backup AS SELECT * FROM customers;
```

### 快速查看表結構
```sql
SHOW CREATE TABLE table_name;
DESCRIBE table_name;
```

### 清除測試資料
```sql
DELETE FROM table_name WHERE condition;
TRUNCATE TABLE table_name;  -- 清空整個表
```

---

*💡 提示：將此參考卡片保存在手邊，隨時查閱！*