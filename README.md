JacpFX / DataFX-flow plugin
=============


This plugin allows you to integrate DataFX-flows in JacpFX. You can inject a JacpFX context ( @Resource private Context context) to any DataFX-flow controller and access the rest of your JacpFX application.

## Usage
The DataFXFlowWrapper class extends the DataFX Flow.class, you must provide a valid JacpFX component id of a component who's context should be injectable to a DataFX-flow controller. You can find an example in the "SimpleDataFX_JacpFX" project included in the ComponentLeft component.