package br.pro.hashi.ensino.desagil.aps.model;

public class XnorGate extends Gate {
    protected final NandGate nandLeft;
    protected final NandGate nandTop;
    protected final NandGate nandBottom;
    protected final NandGate nandRight;
    protected final NandGate nandNot;

    public XnorGate() {
        super("XNOR", 2);

        nandLeft = new NandGate();

        nandTop = new NandGate();
        nandTop.connect(1, nandLeft);

        nandBottom = new NandGate();
        nandBottom.connect(0, nandLeft);

        nandRight = new NandGate();
        nandRight.connect(0, nandTop);
        nandRight.connect(1, nandBottom);

        nandNot = new NandGate();
        nandNot.connect(0, nandRight);
        nandNot.connect(1, nandRight);
    }

    @Override
    public boolean read() {
        return nandNot.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        switch (inputIndex) {
            case 0:
                nandTop.connect(0, emitter);
                nandLeft.connect(0, emitter);
                break;
            case 1:
                nandLeft.connect(1, emitter);
                nandBottom.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputIndex);
        }
    }
}
