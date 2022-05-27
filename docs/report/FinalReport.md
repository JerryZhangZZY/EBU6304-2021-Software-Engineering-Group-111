# FINAL REPORT GROUP 111

[TOC]

## Project management

*Project management in your team working. E.g., using project
management techniques, tools, planning, estimating, decision making
and adapting to changes.*

- version control/dev platform
  - github
    - commit
    - issue
    - milestone
    - insight
    - branch
    - release
    - actions
  - gitee
    - mirror
    - connection problem
- journal
  - github
    - log
  - qmplus hub
    - development timeline
    - minutes of meetings
- communication
  - qmplus hub
    - forum
    - tencent meeting
    - wechat
    - github/email
- scheduling 
  - progress barchart
- planning
  - group meetings before iterations
  - milestones/ddl
- have a break!

## Requirements

*• Apply the requirements finding techniques.
• User stories, including estimation and prioritise of user stories.
• Iterations planning.
• Prototype.
• Adapt to changes.*

- requirements findingg
  - held meeting before each iteration
    - source of idea
      - questionire
      - go to real airport
      - website
      - our new idea
    - epics
  - write stories
  - backlog
- estimating
  - new issues, now or delay
- prototyping

## Analysis and Design

*• A set of design class diagrams describing the design of the software, show
the class relationships. Note that your design should address the issue of
reusability of software components. You should provide clear justification
for your proposed approach and show that your design is adaptable to
change where necessary.
• Discuss the design of the software.
• Discuss the extent to which your design and the code that implements it
meets the main design principles of programming.*

- analysis
  - class sterotypes
  - association
- design
  - modular
  - coupling

## Implementation and Testing

*• Discuss the implementation strategy and iteration/built plan.
• Discuss the test strategy and test techniques you have used in your
testing.
EBU6304 – Software Engineering (2021/22) Page 5 of 7
• Discuss the using of TDD. Note: TDD is not required for developing the
whole software, however, you should try to use TDD to develop a few
programs.*

### Implementation

#### Component Management

The project is relatively big in its size, and thus it is very important to properly managing various components before writing codes. We divide source codes into two separate modules, `back-end-system` and `kiosk`. Other components are organized in `database`, `lib`, `out`, .etc.

For detailed component structure, vide chart below. Component stereotypes are appended behind,

```
├─ back-end-system  <<minor module>>
├─ conf             <<(auto-generated supporting) file>>
├─ database         <<database table>>
├─ docs             <<documents>>
├─ kiosk            <<major module>>
│   ├ icons         <<(supporting) file>>
│   └ src           <<s(ource code) file>>
├─ lib              <<library>>
├─ out              <<executable>>
└─ printer-output   <<software output>>
```

Among all directories, `src` is the core of this project, which contains all the codes of the kiosk programme. With in this directory, we use package to implement subsystems. To be specific, we applied a hierarchical structure to organize classes. We classify boundary classes, or GUI classes into `frame`, `panel`, and `card` (small panel or sub-panel) packages. And control classes to access database are held in `...Reader/Writer` packages. `main` package are for some system-level classes.

![](report images/packages.png "packages")

#### Traceability

As we know, in the process of implementation, an important principle is to make sure every implementation component traces the corresponding design elements. This requires developers to map carefully design to code.

These figures show how classes and database tables in implementation can be traced back to design stage.

![](report images/tracing1.png)

![](report images/tracing2.png)

#### Build

#### Elegance of Code (?)

### Testing
 - from design
 - build/commit
 - iteration/branch
 - testing
   - unit
   - system

## Appendix

### All reports should include a list of references in the appendix.

- jdk
- junit
- qr code
- csv/json/yaml

### Main screenshots of the system should be included in the appendix.