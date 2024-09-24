# TeachManagement

基于JavaSwing的教学管理系统

### 一.需求分析

1.1.需求分析
在设计教学管理系统之前，需要进行需求分析，明确系统应具备的功能和特性，以满足教育机构和用户的需求。主要业务包括：用户登录、用户管理、课程管理、选课管理、成绩管理。系统功能模块设计如图所示:
![image](https://github.com/user-attachments/assets/6867fe2f-dbbd-49d9-93a2-20ee7e4d54d0)

1.2.用例建模
![image](https://github.com/user-attachments/assets/5e14014c-fca4-4c9c-a29d-5a699e7cfb0e)


1.3.用例描述

1.3.1.用户登录用例描述
用例编号 CASE01
用例名称 用户登录
用例描述 用户进行登录操作
前置条件 用户账户存在并且有效
后置条件 用户成功登录系统
活动步骤 1.用户输入用户名和密码
2.用户选择用户身份
3.用户点击登录按钮，系统验证用户信息，如果用户名和密码正确，登录系统
扩展点 A1.用户登录失败
1.如果用户输入的凭据无效，则提示用户名或密码错误。

1.3.2.用户管理用例描述
用例编号 CASE02
用例名称 用户管理
用例描述 管理员对系统用户进行管理
前置条件 管理员用户已登录
后置条件 管理员用户对系统用户进行CURD操作
活动步骤 1.管理员打开用户管理页面，如教师用户管理、学生用户管理。
2.系统显示用户列表，包括用户的基本信息和操作选项。
3.管理员选择要执行的操作，如添加用户、修改用户、删除用户等。
4.管理员提供所需的用户信息，如用户名、密码。
5.管理员执行相应的操作。
6.添加用户：系统验证提供的用户信息有效且唯一，创建新用户并将其添加到用户列表。
7.修改用户：系统更新用户的信息。
8.删除用户：系统从用户列表中移除相应的用户。
9.系统显示操作成功的提示消息或状态更新。
10.管理员可以继续执行其他用户管理操作。
扩展点 A1. 用户信息验证失败：
1.系统验证所提供的用户信息无效或不完整。
2.系统显示错误消息，指示所提供的用户信息有误。
3.管理员根据错误提示信息进行修正、补充或重新尝试操作。

1.3.3.课程管理用例描述
用例编号 CASE03
用例名称 课程管理
用例描述 管理员对课程进行管理
前置条件 管理员用户已登录
后置条件 管理员用户对课程进行CURD操作
活动步骤 1.管理员打开课程管理页面。
2.系统显示课程列表，包括课程的基本信息和操作选项。
3.管理员选择要执行的操作，如添加课程、修改课程、删除课程等。
4.管理员提供所需的课程信息，如用课程编号、课程名、学分。
5.管理员执行相应的操作。
6.添加课程：系统验证提供的用户信息有效且唯一，创建新课程并将其添加到课程列表。
7.修改课程：系统更新课程的信息。
8.删除课程：系统从课程列表中移除相应的课程。
9.系统显示操作成功的提示消息或状态更新。
10.管理员可以继续执行其他课程管理操作。
扩展点 A1. 课程信息验证失败：
1.系统验证所提供的课程信息无效或不完整。
2.系统显示错误消息，指示所提供的课程信息有误。
3.管理员根据错误提示信息进行修正、补充或重新尝试操作。

1.3.4.成绩审核用例描述
用例编号 CASE04
用例名称 成绩审核
用例描述 管理员对教师录入的成绩进行审核
前置条件 管理员用户已登录，教师已录入对应成绩。
后置条件 管理员成功审核了学生的成绩，并且系统中的成绩和审核状态得到相应的更新。
活动步骤 1.管理员打开成绩审核页面。
2.系统显示待审核的成绩列表，包括学生姓名、科目、成绩和审核状态。
3.管理员选择要审核的特定成绩。
5.管理员根据自己的判断和评估，对成绩进行审核。
6.管理员选择审核结果，如审核驳回、审核通过。
7.管理员提交审核结果。
8.系统更新成绩的审核状态。
9.系统显示操作成功的提示消息或状态更新。
10.管理员可以继续审核其他成绩或执行其他相关操作。
扩展点 A1. 审核不通过：
1.管理员选择审核结果为 "审核驳回"。
2.系统要求管理员提供修改意见或要求。
3.管理员输入修改意见或要求。
4.管理员提交修改内容。
5.系统更新成绩的审核状态为 "审核驳回"，并记录修改意见。
6.系统显示操作成功的提示消息或状态更新。

1.3.5.数据备份用例描述
用例编号 CASE05
用例名称 数据备份
用例描述 管理员对数据库进行备份
前置条件 管理员用户已登录
后置条件 执行系统数据的备份操作，确保数据的安全性和可恢复性
活动步骤 1.系统管理员打开数据备份页面或进入数据备份功能。
2.系统要求管理员选择备份文件存储位置。
3.系统管理员提供备份的设置和选项，如备份路径、备份文件命名等。
4.系统管理员确认备份设置，并启动备份过程。
5.系统执行备份操作，包括将数据复制到备份目标位置、生成备份文件等。
6.备份过程完成后，系统显示备份成功的提示消息或状态更新。
7.系统管理员可以继续执行其他相关的数据管理操作。
扩展点 如果恢复过程中发生错误，系统应该记录错误信息并通知管理员或操作员。管理员或操作员可以尝试重新执行恢复操作或采取其他恢复措施。

1.3.6.课程开课用例描述
用例编号 CASE06
用例名称 课程开课
用例描述 该用例描述了教用户师如何开课。
前置条件 教师用户已登录
后置条件 教师成功创建了一门新的课程，并可以在系统中进行课程管理和相关操作。
活动步骤 1.教师登录系统，并导航到课程管理页面。
2.教师选择开课的选项。
3.教师填写课程信息，包括课程名称、上课地点、上课时间等信息。
4.系统验证课程信息的完整性和有效性。
5.系统创建新的课程，并分配唯一的开课编号。
6.系统将课程添加到教师的课程列表中，并显示操作成功的提示消息。
扩展点 如果系统验证课程信息不完整或无效，系统将显示相应的错误信息，并要求教师进行修正。

1.3.7.成绩录入用例描述
用例编号 CASE07
用例名称 成绩录入
用例描述 该用例描述了教师如何录入学生成绩的过程。
前置条件 教师用户已登录，学生已选修对应课程。
后置条件 教师成功录入了学生的成绩，并可以进行其他相关操作，如成绩查询和统计等。
活动步骤 1.教师导航到成绩录入页面。
2.系统显示待录入成绩的学生列表。
3.教师选择要录入成绩的特定学生。
4.系统显示选择学生的成绩信息，包括学生姓名、科目和当前成绩（如果已有）。
5.教师输入或修改学生的成绩。
6.教师确认成绩录入完成。
7.系统保存并更新学生的成绩信息。
8.系统显示操作成功的提示消息。
扩展点 无

1.3.8.成绩统计用例描述
用例编号 CASE08
用例名称 成绩统计
用例描述 该用例描述了教师如何对学生成绩进行统计分析的过程。
前置条件 教师用户已登录，学生的成绩信息已经存在于系统中。
后置条件 教师成功进行了学生成绩的统计分析。
活动步骤 1.教师导航到成绩统计页面。
2.教师选择要进行统计的特定依据。
3.教师触发统计操作。
4.系统显示统计结果，包括平均成绩，最高成绩，最低成绩，及格率。
扩展点 如果教师选择了无效的科目或统计方法，系统将显示相应的错误信息，并要求教师进行修正。

1.3.9.成绩查询用例描述
用例编号 CASE09
用例名称 成绩查询
用例描述 该用例描述了学生和教师如何查询成绩的过程。
前置条件 学生成绩已经存在于系统中。
学生或教师已经登录系统。
后置条件 学生或教师成功查询了成绩，并可以查看、导出或进行其他相关操作。
活动步骤 1.学生或教师导航到成绩查询页面。
2.系统显示可进行查询的选项，如按科目、按学期或按学生等。
3.学生或教师选择查询选项，并提供相应的查询条件。
4.系统根据选择的查询选项和条件执行查询操作。
5.系统检索并显示符合查询条件的成绩信息。
6.学生或教师可以查看查询结果，并进行进一步操作，如导出成绩单或查看详细成绩。
7.学生或教师可以选择继续进行其他查询或结束查询操作。
扩展点 如果查询条件无效或不完整，系统将显示相应的错误信息，并要求学生或教师进行修正。

1.3.10.课程选课用例描述
用例编号 CASE10
用例名称 课程选课
用例描述 该用例描述了学生如何在系统中进行课程选课的过程。
前置条件 学生已经登录系统。
系统中存在可选修的课程信息。
后置条件 学生成功选修了一门课程，并可以在系统中查看已选课程和相关信息。
活动步骤 1.学生导航到课程选课页面。
2.系统显示当前可选修的课程列表。
3.学生浏览课程列表，查看课程名称、教师、时间和地点等信息。
4.学生选择需要选修的课程，并点击选课按钮。
5.系统检查学生是否满足课程的先修条件和选课限制。
6.如果学生满足先修条件和选课限制，系统将学生添加到该门课程的选课名单中，并显示选课成功的提示消息。
7.如果学生不满足先修条件或选课限制，系统将显示相应的错误信息，并提示学生无法选修该门课程。
8.学生可以选择继续选修其他课程或结束选课操作。
扩展点 如果学生不满足选课限制，系统将显示相应的错误信息，并要求学生选择其他课程或满足相应的条件。

### 二.系统设计

2.1.数据库设计
2.1.1.数据库概念结构设计（E-R图）
![image](https://github.com/user-attachments/assets/1c0f1622-8749-4d94-8668-816436de1f8b)

2.1.2.数据库逻辑结构设计（关系模式）

（1）Student（SNo, SName, SSex, SClass, enrollTime，pwd）
（2）Teacher（TNo, TName,pwd）
（3）Course（CNo, CName, CCredit）
（4）Score（SNo, CNo, TNo, grade，term，status，comment）
（5）Teach（id, CNo, TNo, CRoom, CTime）
（6）Users（id, uname, pwd）

（1）学生：（学号、姓名、性别、班级、入学年份、密码） 
（2）教师：（工号、姓名、密码） 
（3）课程：（课号、课名、学分）
（4）成绩：（学号、课号、教师工号、成绩、学期、审核状态、审核备注）
（5）开课：（id、课号、教师工号、上课地点、上课时间） 
（6）管理员：（id、账号、密码）

2.1.3.数据库物理结构设计
![image](https://github.com/user-attachments/assets/747ded3c-6548-4258-8e65-f921624a4517)

3.2.系统架构设计
系统采用MVC三层架构设计，Model层负责处理应用程序的核心业务逻辑，DAO类则提供了与数据库进行交互的接口，而JFrame作为视图层，负责呈现用户界面。通过将业务逻辑、数据访问和用户界面分离，MVC架构使得各个部分之间的耦合度降低，便于进行模块化开发和维护。
2.3.模块设计
以成绩录入为例。

2.3.1.成绩录入类图
成绩录入功能模块涉及到的类有Score类、ScoreDao类，以及JFrame页面类。
![image](https://github.com/user-attachments/assets/5e87205a-834f-486f-bfd7-13bcf3bb8c93)

2.3.2.成绩录入时序图
![image](https://github.com/user-attachments/assets/b49490ba-aa06-4302-97fe-ed19db97371f)

三.产品实现
3.1.教师开课模块设计
3.1.1.界面设计
![image](https://github.com/user-attachments/assets/2cb093df-e316-48eb-b20d-d4e73f42427a)

3.1.2.功能实现
public static int openCourse(String TNo, String CName, String CRoom, String CTime) {
 // 在Course表中查找是否有对应课程
 String sqlSelect = "SELECT CNo FROM Course WHERE CName = ?";
 try (Connection conn = DbUtil.getConnection();
 PreparedStatement psSelect = conn.prepareStatement(sqlSelect)) {
 psSelect.setString(1, CName);
 ResultSet rs = psSelect.executeQuery();
 if (!rs.next()) {
 // 如果没有对应课程，则无法开课，返回1
 return 1;
 }
 String CNo = rs.getString("CNo");
 rs.close();
 // 在Teach表中插入TNo、CNo、CRoom和CTime
 String sqlInsert = "INSERT INTO Teach (TNo, CNo, CRoom, CTime) VALUES ( ?, ?, ?, ?)";
 try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
 psInsert.setString(1, TNo);
 psInsert.setString(2, CNo);
 psInsert.setString(3, CRoom);
 psInsert.setString(4, CTime);
 int count = psInsert.executeUpdate();
 if (count == 1) {
 // 成功插入，返回0  
 return 0;
 } else {
 // 插入失败，返回2表示其他错误
 return 2;
 }
 }
 } catch (SQLException e) {
 e.printStackTrace();
 // 抛出异常，返回2表示其他错误
 return 2;
 }
 return 0;
 }
3.2.成绩审核模块设计
3.2.1.界面设计
![image](https://github.com/user-attachments/assets/e9155a2a-aca4-4268-acda-4757d3ec7089)

3.2.2.功能实现

```java
     //审核通过
    public static int  AuditPass(String SNo,String TNO,String CNo) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        try {
            conn = DbUtil.getConnection();
            String sql = "update score set status = '审核通过' where SNo = ? and TNo = ? and CNo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, SNo);
            stmt.setString(2, TNO);
            stmt.setString(3, CNo);
            result = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DbUtil.closeConnection(conn);
        }
        return result;
    }
```

```java
    //审核驳回
    public static int  AuditFail(String SNo, String TNO, String CNo, String comment) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        try {
            conn = DbUtil.getConnection();
            String sql = "update score set status = '审核驳回',comment = ? where SNo = ? and TNo = ? and CNo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,comment);
            stmt.setString(2, SNo);
            stmt.setString(3, TNO);
            stmt.setString(4, CNo);
            result = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DbUtil.closeConnection(conn);
        }
        return result;
    }
```

3.3.成绩查询模块设计
3.3.1.界面设计
![image](https://github.com/user-attachments/assets/4ac330f4-8418-4529-ad80-17c53c610283)

3.3.2.功能实现

```java
  //将JTable的数据导出到Excel中
  public static void exportGradeSheet(JTable table, String filePath) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try (FileWriter writer = new FileWriter(filePath)) {
            // 写入表头
            for (int column = 0; column < model.getColumnCount(); column++) {
                writer.write(model.getColumnName(column) + "\t");
            }
            writer.write("\n");
            // 写入每行数据
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int column = 0; column < model.getColumnCount(); column++) {
                    writer.write(model.getValueAt(row, column).toString() + "\t");
                }
                writer.write("\n");
            }
            showMessageBox("导出成绩成功！");
        } catch (IOException e) {
            showErrorMessage("导出成绩失败！");
        }
    }
    //通过JFileChooser选择导出文件位置
    public static String chooseSaveLocation() {
        String initialFileName = "score.xlsx";
        String initialDirectory = "C://";
        JFileChooser fileChooser = new JFileChooser(initialDirectory);
        // 设置保存文件的过滤器（可选）
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel文件 (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        // 设置初始文件名
        fileChooser.setSelectedFile(new File(initialFileName));
        // 显示文件选择对话框
        int result = fileChooser.showSaveDialog(null);
        fileChooser.setVisible(true);
        // 处理用户选择的结果
        if (result == JFileChooser.APPROVE_OPTION) {
            // 获取用户选择的文件路径
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            return filePath;
        }
        return null; // 用户取消了选择
    }
    //导出成绩按钮事件
    private void GradeExportButtonMouseClicked(MouseEvent e) {
        String filePath = chooseSaveLocation();
        exportGradeSheet(table2, filePath);
    }
```

### 四.产品测试

4.1.测试方法与策略
在开发过程中，本系统采纳了单元测试法这一传统测试手段，对每一段功能代码进行细致的测试，旨在减少潜在的代码缺陷。单元测试法具备多项优势。首先，通过逐步编写和测试程序，确保每段功能代码的准确性，从而减少整体程序的问题。即便在开发后期，我们也可以根据项目需求轻松添加所需功能，而不必担心破坏程序核心。由于从始至终的逐步验证，我们可以确保程序核心的稳定性。其次，在编写单元测试的过程中，我们能够从用户角度思考问题，全面考虑实际使用中可能遇到的各类问题，并进行一一测试。通过将每个单元功能分开测试，降低程序间的耦合度，提高了代码的可维护性和可扩展性。
4.2.功能测试
4.2.1.开课功能测试
教师开课使用不存在的课程名，系统提示不存在课程，如图所示。
![image](https://github.com/user-attachments/assets/5b809773-5b91-4d0b-bee9-8ac4e664d881)

4.2.2.成绩录入测试
 教师录入不合法的成绩，系统提示成绩输入不合法，如图所示。
![image](https://github.com/user-attachments/assets/da0ec056-3c49-4699-af3c-a5061be606c6)

### 五.结论

5.1.系统设计成果特点

- 基于Java Swing: 教学管理系统的前端采用Java Swing技术进行开发，具有良好的跨平台性和用户界面交互性。Java Swing提供了丰富的UI组件和布局管理器，使得系统界面设计美观、易用、响应迅速。
  
- 成绩审核: 教学管理系统具备成绩审核功能，可以方便教师用户对学生的成绩进行审核和修改。该功能支持对成绩的查看、修改和添加备注等操作，确保成绩的准确性和完整性。
  
- 选课管理: 教学管理系统提供了完善的选课管理功能，学生可以通过系统进行选课，教师用户可以查看和管理选课信息。
  
- 数据库备份: 教学管理系统具备数据库备份功能，通过备份系统中的数据，可以避免数据丢失或损坏的风险，并提供了数据恢复的手段，保障数据的安全性和可靠性。
  
- 成绩导出: 教学管理系统支持成绩导出功能，可以将学生成绩以Excel或其他常见格式导出，方便教师用户进行数据分析和报告生成。*
